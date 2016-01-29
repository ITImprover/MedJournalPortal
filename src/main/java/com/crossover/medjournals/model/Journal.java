package com.crossover.medjournals.model;

public class Journal {
    private String name;

    private Integer publisherId;

    public Journal(String name, Integer publisherId) {
        this.name = name;
        this.publisherId = publisherId;
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
}
