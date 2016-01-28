package com.crossover.medjournals;

import javax.annotation.Resource;
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

    private static final String IS_USER_EXISTS_SQL = "SELECT id FROM MED_JOURNALS.USERS WHERE EMAIL = ?";
    private static final String ADD_USER_SQL
            = "INSERT INTO MED_JOURNALS.USERS (EMAIL, PASSWORD, JOURNAL_NAME) VALUES (?, ?, ?)";
    private static final String LOGIN_SQL = "SELECT JOURNAL_NAME FROM MED_JOURNALS.USERS WHERE EMAIL = ? AND PASSWORD = ?";
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
        } else if (journalName.length() == 0) {
            throw new UserException("password cannot be empty");
        } else if (!password.equals(passwordAgain)) {
            throw new UserException("Passwords are not equal");
        } else {
            Connection connection = dataSource.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement(IS_USER_EXISTS_SQL);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    throw new UserException("user already exists");
                } else {
                    ps = connection.prepareStatement(ADD_USER_SQL);
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ps.setString(3, journalName);
                    ps.execute();
                }
            } finally {
                connection.close();
            }
        }
    }

    /**
     * authenticate user
     *
     * @param email
     * @param password
     * @return user id
     */
    public String login(String email, String password) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(LOGIN_SQL);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();
            return result.next() ? result.getString("JOURNAL_NAME") : null;
        } finally {
            connection.close();
        }
    }

}
