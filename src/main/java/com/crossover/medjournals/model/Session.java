package com.crossover.medjournals.model;

/**
 * User Session
 */
public class Session {

    private Integer userId;

    private String journalName;

    public Session(Integer userId, String journalName) {
        this.userId = userId;
        this.journalName = journalName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }
}
