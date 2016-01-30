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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") != null) {
            if (request.getSession().getAttribute("journalName") != null) {
                redirect(response, PUBLISHER_ISSUES);
            } else {
                redirect(response, SUBSCRIPTIONS);
            }
        } else {
            redirect(response, LOGIN_JSP);
        }
    }


}
