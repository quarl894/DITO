package com.example.youngjung.dito.Model;

import java.util.ArrayList;

public class member {
    // id, name, pic,
    private String id;
    private String name;
    private String subnail;
    private ArrayList<String> room_key;

    public member() {
    }

    public member(String id) {
        this.id = id;
    }



    public member(String id, String name, String subnail) {
        this.id = id;
        this.name = name;
        this.subnail = subnail;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubnail() {
        return subnail;
    }

    public void setSubnail(String subnail) {
        this.subnail = subnail;
    }

    public ArrayList<String> getRoom_key() {
        return room_key;
    }

    public void setRoom_key(ArrayList<String> room_key) {
        this.room_key = room_key;
    }
}
