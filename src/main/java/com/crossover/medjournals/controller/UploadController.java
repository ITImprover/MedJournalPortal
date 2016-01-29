package com.crossover.medjournals.controller;

import com.crossover.medjournals.controller.UserManagement.RegisterController;
import com.crossover.medjournals.dao.IssueException;
import com.crossover.medjournals.dao.IssueService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/upload")
public class UploadController extends AbstractServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private static final String UPLOAD_JSP = "uploadIssue.jsp";
    private IssueService issueService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        issueService = new IssueService(dataSource);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!"".equals(request.getSession().getAttribute("journalName"))) {
            response.setContentType("text/html;charset=UTF-8");

            // Create path components to save the file
            final Part filePart = request.getPart("issue");
            final String fileName = getFileName(filePart);

            InputStream fileContent = null;
            try {
                fileContent = filePart.getInputStream();

                issueService.addIssue((Integer) request.getSession().getAttribute("userId"), fileName, fileContent);
                redirect(response, INDEX_JSP);
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "You either did not specify a file to upload or are trying to upload "
                        + "a file to a protected or nonexistent location.");
                forward(request, response, UPLOAD_JSP);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "Something going wrong");
                forward(request, response, UPLOAD_JSP);
            } catch (IssueException e) {
                request.setAttribute("errorMessage", e.getMessage());
                forward(request, response, UPLOAD_JSP);
            } finally {
                if (fileContent != null) {
                    fileContent.close();
                }
            }
        } else {
            request.setAttribute("errorMessage", "You don't have permission for uploading");
            redirect(response, INDEX_JSP);
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
