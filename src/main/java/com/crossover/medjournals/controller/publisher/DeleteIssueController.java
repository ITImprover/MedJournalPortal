package com.crossover.medjournals.controller.publisher;

import com.crossover.medjournals.controller.AbstractController;
import com.crossover.medjournals.dao.IssueService;
import com.crossover.medjournals.dao.SubscriptionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/deleteIssue")
public class DeleteIssueController extends AbstractController {

    public static final Logger LOGGER = Logger.getLogger(DeleteIssueController.class.getName());
    private IssueService issueService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        issueService = new IssueService(dataSource);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null && request.getSession().getAttribute("journalName") != null) {
            try {
                issueService.delete(Integer.parseInt(request.getParameter("id")));
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "Something going wrong");
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "issue id is incorrect", e);
                request.setAttribute("errorMessage", "invalid URL");
            } finally {
                forward(request, response, PUBLISHER_ISSUES);
            }
        } else {
            redirect(response, MAIN_PAGE);
        }
    }

}
