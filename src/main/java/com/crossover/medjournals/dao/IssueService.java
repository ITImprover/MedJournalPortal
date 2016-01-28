package com.crossover.medjournals.dao;

import javax.sql.DataSource;
import java.io.InputStream;
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
public class IssueService {

    private static final String ADD_ISSUE_SQL
            = "INSERT INTO MED_JOURNALS.ISSUES (USER_ID, NAME, CONTENT) VALUES (?, ?, ?)";
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
}
