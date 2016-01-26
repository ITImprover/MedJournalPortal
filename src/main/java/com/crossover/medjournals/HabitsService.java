/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossover.medjournals;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author d_alex
 */
public class HabitsService {
    private static final String ADD_HABIT_SQL = "INSERT INTO HABITATOR01.HABITS (username, name) VALUES (?, ?)";
    private static final String GET_HABITS_LIST_SQL = "SELECT name FROM HABITATOR01.HABITS WHERE username = ?";
    private static final String GET_USERS_LIST_BY_HABIT_NAME_SQL = "SELECT u.id, u.name "
            + "FROM HABITATOR01.USERS u, HABITATOR01.HABITS h WHERE u.id = h.username and h.NAME = ?";

    private DataSource dataSource;

/*    public HabitsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Set<String> getHabitsForUser(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_HABITS_LIST_SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Set<String> list = new HashSet<String>();
            while(rs.next()){
                list.add(rs.getString("name"));
            }
            return list;
        } finally {
            connection.close();
        }
    }

    public void addHabit(int userId, String habitName) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(ADD_HABIT_SQL);

            ps.setInt(1, userId);
            ps.setString(2, habitName);

            ps.execute();
        } finally {
            connection.close();
        }
    }

    public List<User> getUsersByHabitName(String habitName) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USERS_LIST_BY_HABIT_NAME_SQL);
            ps.setString(1, habitName);
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList<User>();
            while(rs.next()){
                User user = new User(rs.getInt("id"), rs.getString("name"));
                list.add(user);
            }
            return list;
        } finally {
            connection.close();
        }
    }
*/
}
