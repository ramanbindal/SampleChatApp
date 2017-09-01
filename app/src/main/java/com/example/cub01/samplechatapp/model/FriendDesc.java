package com.example.cub01.samplechatapp.model;

import java.util.Date;

/**
 * Created by cub11 on 9/1/2017.
 */

public class FriendDesc {
    public int id;
    public String name;
    public String place;
    public Date dob;
    public String college;
    public String contact;

    public FriendDesc(String place,Date dob,String college,String contact){
        this.college=college;
        this.contact=contact;
        this.place=place;
        this.dob=dob;

    }

    public String getPlace(){
        return place;
    }
    public Date getDob(){
        return dob;

    }
    public String getCollege(){
        return college;
    }
    public String getContact(){
        return contact;
    }
}
