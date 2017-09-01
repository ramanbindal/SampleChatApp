package com.example.cub01.samplechatapp.model;

/**
 * Created by cub11 on 9/1/2017.
 */

public class FriendStatus {
    public int id;
    public String name;
    public String status;

    public FriendStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }
}
