package com.crossover.medjournals;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegisterController extends AbstractController {
    public static final String REGISTRATIONSP = "registration.jsp";

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
            String journalName = request.getParameter("journalName");
            String password = request.getParameter("password");
            String passwordAgain = request.getParameter("passwordAgain");

            userService.registerUser(email, password, passwordAgain, journalName);

            request.getSession().setAttribute("username", journalName);
            request.getSession().setAttribute("isLoggedIn", true);
            redirectTo(request, response, INDEXJSP);
        } catch (SQLException e) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Something going wrong");
            redirectTo(request, response, REGISTRATIONSP);
        } catch (UserException e) {
            request.setAttribute("errorMessage", e.getMessage());
            redirectTo(request, response, REGISTRATIONSP);
        }
    }

}
