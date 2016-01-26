package com.crossover.medjournals;

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
    public static final String INDEXJSP = "index.jsp";
    @Resource(name = "jdbc/JournalsDB")
    protected DataSource dataSource;

    protected void redirectTo(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
        request.getRequestDispatcher(jspName).forward(request, response);
    }
}
