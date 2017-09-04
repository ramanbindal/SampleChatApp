package com.example.cub01.samplechatapp.model;

/**
 * Created by cub11 on 9/1/2017.
 */

public class FriendStatus {
    public int id;

    public String status;

    public FriendStatus(int id, String status) {
        this.status = status;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getId();
    }
}
