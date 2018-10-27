package com.example.youngjung.dito.Model;

import java.util.ArrayList;

public class room {
    // r_name, s_name, participant, homework
    private String r_name;
    private String s_name;
//    private ArrayList<member> participant;
//    private ArrayList<String> homework;

    public room() {
    }

    public room(String r_name, String s_name) {
        this.r_name = r_name;
        this.s_name = s_name;
    }


    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getS_name() {
        return s_name;
    }
}
