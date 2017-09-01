package com.example.cub01.samplechatapp.model;

/**
 * Created by cub11 on 9/1/2017.
 */

public class Friends {
    public int id;
    public String name;
    public int blocked;

    public Friends(int id,String name,int blocked){
        this.blocked=blocked;
        this.id=id;
        this.name=name;
    }

    public int getBlocked(){
        return blocked;

    }

    public String getName(){
        return name;
    }
}
