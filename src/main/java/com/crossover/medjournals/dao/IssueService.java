package com.crossover.medjournals.dao;

import com.crossover.medjournals.model.Journal;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author d_alex
 */
public class IssueService {

    private static final String ADD_ISSUE_SQL
            = "INSERT INTO MED_JOURNALS.ISSUES (USER_ID, NAME, CONTENT) VALUES (?, ?, ?)";
    private static final String GET_PUBLISHER_ISSUES_SQL
            = "SELECT NAME FROM MED_JOURNALS.ISSUES WHERE USER_ID = ?";
    private static final String GET_SUBSCRIPTIONS_SQL = "SELECT NAME, USER_ID FROM MED_JOURNALS.ISSUES WHERE USER_ID = ?";
    private DataSource dataSource;

    public IssueService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param userId
     * @param fileName
     * @param fileContent
     * @throws IssueException
     * @throws SQLException
     */
    public void addIssue(Integer userId, String fileName, InputStream fileContent) throws IssueException, SQLException {
        if (fileName.length() == 0) {
            throw new IssueException("Invalid file name");
        }
        int pdfExtensionIndex = fileName.toLowerCase().lastIndexOf(".pdf");
        if (pdfExtensionIndex == -1) {
            throw new IssueException("You may upload PDF file only");
        }
        String issueName = fileName.substring(0, pdfExtensionIndex);
        if (issueName.length() == 0) {
            throw new IssueException("Issue name cannot be empty");
        } else {
            Connection connection = dataSource.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement(ADD_ISSUE_SQL);
                ps.setInt(1, userId);
                ps.setString(2, issueName);
                ps.setBlob(3, fileContent);
                ps.execute();
            } finally {
                connection.close();
            }
        }
    }

    public List<String> getIssuesByUserId(Integer userId) throws SQLException {
        List<String> result = new ArrayList<String>();
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_PUBLISHER_ISSUES_SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("NAME"));
            }
        } finally {
            connection.close();
        }
        return result;
    }

    public List<Journal> getSubscriptionsByUserId(Integer userId) {
        List<Journal> result = new ArrayList<Journal>();
//        Connection connection = dataSource.getConnection();
//        try {
//            PreparedStatement ps = connection.prepareStatement(GET_SUBSCRIPTIONS_SQL);
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                result.add(rs.getString("NAME"));
//            }
//        } finally {
//            connection.close();
//        }
        return result;
    }
}
