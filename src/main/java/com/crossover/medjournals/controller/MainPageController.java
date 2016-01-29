package com.crossover.medjournals.controller;

import com.crossover.medjournals.dao.IssueService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainPageController extends AbstractController {

    private static final String PUBLISHER_ISSUES = "publisherIssues";
    private static final String SUBSCRIBER_JOURNALS = "subscriptions";
    private IssueService issueService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") != null) {
            if (!"".equals(request.getSession().getAttribute("journalName"))) {
                redirect(response, PUBLISHER_ISSUES);
            } else {
                redirect(response, SUBSCRIBER_JOURNALS);
            }
        } else {
            redirect(response, LOGIN_JSP);
        }
    }


}
