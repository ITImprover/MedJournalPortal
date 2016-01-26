package com.crossover.medjournals;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginController extends AbstractController {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService(dataSource);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String journalName = userService.login(email, password);

            if (journalName == null) {
                request.setAttribute("errorMessage", "Incorrect e-mail or password");
            } else {
                request.getSession().setAttribute("username", journalName);
                request.getSession().setAttribute("isLoggedIn", true);
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Something going wrong");
        } finally {
            redirectTo(request, response, INDEXJSP);
        }
    }

}
