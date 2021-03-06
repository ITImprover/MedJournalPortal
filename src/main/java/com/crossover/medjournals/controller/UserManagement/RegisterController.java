package com.crossover.medjournals.controller.UserManagement;

import com.crossover.medjournals.controller.AbstractController;
import com.crossover.medjournals.exception.UserException;
import com.crossover.medjournals.dao.UserService;

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
    public static final String REGISTRATION_JSP = "registration.jsp";
    public static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

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
            journalName = journalName.length() > 0 ? journalName : null;
            String password = request.getParameter("password");
            String passwordAgain = request.getParameter("passwordAgain");
            userService.registerUser(email, password, passwordAgain, journalName);
            Integer userId = userService.getUserIdByEmail(email);
            if (userId == null) {
                request.setAttribute("errorMessage", "Registration failed");
                forward(request, response, REGISTRATION_JSP);
            } else {
                request.getSession().setAttribute("userId", userId);
                request.getSession().setAttribute("journalName", journalName);
                redirect(response, MAIN_PAGE);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Something going wrong");
            forward(request, response, REGISTRATION_JSP);
//            throw new ServletException(e);
        } catch (UserException e) {
            request.setAttribute("errorMessage", e.getMessage());
            forward(request, response, REGISTRATION_JSP);
        }
    }

}
