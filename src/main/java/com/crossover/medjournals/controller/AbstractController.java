package com.crossover.medjournals.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Денис on 25.01.2016.
 */
public class AbstractController extends HttpServlet {
    protected static final String MAIN_PAGE = "";
    protected static final String LOGIN_JSP = "login.jsp";
    protected static final String PUBLISHER_ISSUES = "publisherIssues";
    protected static final String SUBSCRIPTIONS = "subscriptions";
    @Resource(name = "jdbc/JournalsDB")
    protected DataSource dataSource;

    protected void forward(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
        request.getRequestDispatcher(jspName).forward(request, response);
    }

    protected void redirect(HttpServletResponse response, String jspName) throws ServletException, IOException {
        response.sendRedirect(jspName);
    }

}
