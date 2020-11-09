package com.votingAPI.votingAPI.vote;

public class Vote {
    long id;
    String name;

    public Vote(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
