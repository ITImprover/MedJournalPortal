package com.crossover.medjournals.dao;

import com.crossover.medjournals.exception.IssueException;
import com.crossover.medjournals.model.Issue;

import javax.sql.DataSource;
import java.io.InputStream;
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
public class IssueService {

    private static final String ADD_ISSUE_SQL
            = "INSERT INTO MED_JOURNALS.ISSUES (USER_ID, NAME, CONTENT) VALUES (?, ?, ?)";
    private static final String GET_PUBLISHER_ISSUES_SQL
            = "SELECT ID, NAME FROM MED_JOURNALS.ISSUES WHERE USER_ID = ?";
    private static final String DELETE_ISSUE_SQL
            = "DELETE FROM MED_JOURNALS.ISSUES WHERE ID = ?";
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

    public List<Issue> getIssuesByUserId(Integer userId) throws SQLException {
        List<Issue> result = new ArrayList<Issue>();
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_PUBLISHER_ISSUES_SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Issue(rs.getInt("ID"), rs.getString("NAME")));
            }
        } finally {
            connection.close();
        }
        return result;
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_ISSUE_SQL);
            ps.setInt(1, id);
            ps.execute();
        } finally {
            connection.close();
        }
    }
}
