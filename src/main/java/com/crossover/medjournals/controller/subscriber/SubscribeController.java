package com.crossover.medjournals.controller.subscriber;

import com.crossover.medjournals.controller.AbstractController;
import com.crossover.medjournals.dao.SubscriptionService;
import com.crossover.medjournals.dao.UserService;
import com.crossover.medjournals.model.Session;
import com.crossover.medjournals.model.Subscription;

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

@WebServlet("/subscribe")
public class SubscribeController extends AbstractController {

    public static final Logger LOGGER = Logger.getLogger(SubscribeController.class.getName());
    private SubscriptionService subscriptionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        subscriptionService = new SubscriptionService(dataSource);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null && request.getSession().getAttribute("journalName") == null) {
            try {
                subscriptionService.subscribe(userId, request.getParameter("publisherId"));
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "Something going wrong");
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "publisherId is empty", e);
                request.setAttribute("errorMessage", "invalid URL");
            } finally {
                forward(request, response, SUBSCRIPTIONS);
            }
        } else {
            redirect(response, SUBSCRIPTIONS);
        }
    }

}
