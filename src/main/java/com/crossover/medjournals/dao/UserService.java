package com.crossover.medjournals.dao;

import com.crossover.medjournals.model.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author d_alex
 */
public class UserService {

    private static final String GET_USER_ID_BY_EMAIL_SQL
            = "SELECT id FROM MED_JOURNALS.USERS WHERE EMAIL = ?";
    private static final String ADD_USER_SQL
            = "INSERT INTO MED_JOURNALS.USERS (EMAIL, PASSWORD, JOURNAL_NAME) VALUES (?, ?, ?)";
    private static final String LOGIN_SQL
            = "SELECT ID, JOURNAL_NAME FROM MED_JOURNALS.USERS WHERE EMAIL = ? AND PASSWORD = ?";

    private DataSource dataSource;

    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Register new user
     *
     * @param email - user email
     * @param password - user password
     * @param passwordAgain - user password again
     * @throws UserException
     */
    public void registerUser(String email, String password, String passwordAgain, String journalName)
            throws UserException, SQLException {
        if (email.length() == 0) {
            throw new UserException("email cannot be empty");
        } else if (password.length() == 0) {
            throw new UserException("password cannot be empty");
        } else if (!password.equals(passwordAgain)) {
            throw new UserException("Passwords are not equal");
        } else {
            Connection connection = dataSource.getConnection();
            try {
                Integer id = getUserIdByEmail(email, connection);
                if (id != null) {
                    throw new UserException("user already exists");
                } else {
                    PreparedStatement ps = connection.prepareStatement(ADD_USER_SQL);
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ps.setString(3, journalName);
                    ps.execute();
                    ps = connection.prepareStatement(GET_USER_ID_BY_EMAIL_SQL);
                    ps.setString(1, email);
                }
            } finally {
                connection.close();
            }
        }
    }

    public Integer getUserIdByEmail(String email) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            return getUserIdByEmail(email, connection);
        } finally {
            connection.close();
        }
    }

    private Integer getUserIdByEmail(String email, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_USER_ID_BY_EMAIL_SQL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return null;
        }
    }

    /**
     * authenticate user
     *
     * @param email
     * @param password
     * @return user id
     */
    public Session login(String email, String password) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(LOGIN_SQL);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return null;
            } else {
                return new Session(result.getInt("ID"), result.getString("JOURNAL_NAME"));
            }
        } finally {
            connection.close();
        }
    }

}
