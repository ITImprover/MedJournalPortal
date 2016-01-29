package com.crossover.medjournals.controller.publisher;

import com.crossover.medjournals.controller.AbstractController;
import com.crossover.medjournals.dao.IssueService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/publisherIssues")
public class PublisherIssuesController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(PublisherIssuesController.class.getName());
    private static final String PUBLISHER_ISSUES_JSP = "publisherIssues.jsp";
    private IssueService issueService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        issueService = new IssueService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null && !"".equals(request.getSession().getAttribute("journalName"))) {
            try {
                List<String> issues = issueService.getIssuesByUserId(userId);
                LOGGER.info("issues[]: " + issues);
                //request.setCharacterEncoding("UTF-8");
                request.setAttribute("issues", issues);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "Something going wrong");
            }
            finally {
                forward(request, response, PUBLISHER_ISSUES_JSP);
            }
        } else {
            redirect(response, MAIN_PAGE);
        }
    }


}
