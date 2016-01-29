package com.crossover.medjournals.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainPageServlet extends AbstractServlet {

    private static final String PUBLISHER_ISSUES_JSP = "publisherIssues.jsp";
    private static final String SUBSCRIBER_JOURNALS_JSP = "subscriberJournals.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (request.getSession().getAttribute("userId") != null) {
            if (!"".equals(request.getSession().getAttribute("journalName"))) {
                redirect(response, PUBLISHER_ISSUES_JSP);
            } else {
                redirect(response, SUBSCRIBER_JOURNALS_JSP);
            }
        } else {
            redirect(response, LOGIN_JSP);
        }
    }


}
