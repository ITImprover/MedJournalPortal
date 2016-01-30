package com.crossover.medjournals.controller.subscriber;

import com.crossover.medjournals.controller.AbstractController;
import com.crossover.medjournals.dao.SubscriptionService;
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

@WebServlet("/subscriptions")
public class SubscriptionsController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(SubscriptionsController.class.getName());
    private static final String SUBSCRIPTIONS_JSP = "subscriptions.jsp";
    private SubscriptionService subscriptionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        subscriptionService = new SubscriptionService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null && request.getSession().getAttribute("journalName") == null) {
            try {
                List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
                LOGGER.info("subscriptions[]: " + subscriptions);
                request.setAttribute("subscriptions", subscriptions);
                request.setAttribute("errorMessage", request.getAttribute("errorMessage"));
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
                request.setAttribute("errorMessage", "Something going wrong");
            } finally {
                forward(request, response, SUBSCRIPTIONS_JSP);
            }
        } else {
            redirect(response, MAIN_PAGE);
        }
    }


}
