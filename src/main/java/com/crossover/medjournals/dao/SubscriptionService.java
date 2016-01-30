package com.crossover.medjournals.dao;

import com.crossover.medjournals.model.Subscription;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author d_alex
 */
public class SubscriptionService {

    private static final String GET_SUBSCRIPTIONS_SQL
            = "SELECT JOURNAL_NAME, MED_JOURNALS.USERS.ID PUBLISHER_ID, (SUBSCRIBER_USER_ID IS NOT NULL) SUBSCRIBED"
            + " FROM MED_JOURNALS.USERS"
            + " LEFT JOIN MED_JOURNALS.SUBSCRIPTIONS"
            + " ON MED_JOURNALS.USERS.ID = PUBLISHER_USER_ID AND SUBSCRIBER_USER_ID = ?"
            + " WHERE JOURNAL_NAME IS NOT NULL";
    private static final String SUBSCRIBE_SQL
            = "INSERT INTO MED_JOURNALS.SUBSCRIPTIONS (SUBSCRIBER_USER_ID, PUBLISHER_USER_ID) VALUES (?, ?)";
    private DataSource dataSource;

    public SubscriptionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Subscription> getSubscriptionsByUserId(Integer userId) throws SQLException {
        List<Subscription> result = new ArrayList<Subscription>();
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_SUBSCRIPTIONS_SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Subscription(
                        rs.getString("JOURNAL_NAME"),
                        rs.getInt("PUBLISHER_ID"),
                        rs.getBoolean("SUBSCRIBED")));
            }
        } finally {
            connection.close();
        }
        return result;
    }

    public void subscribe(Integer subscriberId, Integer publisherId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SUBSCRIBE_SQL);
            ps.setInt(1, subscriberId);
            ps.setInt(2, publisherId);
            ps.execute();
        } finally {
            connection.close();
        }
    }
}
