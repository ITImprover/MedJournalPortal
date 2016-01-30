package com.crossover.medjournals.model;

public class Subscription {
    private String name;

    private Integer publisherId;

    private boolean subscribed;

    public Subscription(String name, Integer publisherId, boolean subscribed) {
        this.name = name;
        this.publisherId = publisherId;
        this.subscribed = subscribed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
