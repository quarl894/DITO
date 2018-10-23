package com.example.youngjung.dito.Model;

import java.util.ArrayList;

public class room {
    // r_name, s_name, participant, homework
    private String r_name;
    private String s_name;
    private ArrayList<member> participant;
    private ArrayList<String> homework;

    public room(String r_name, String s_name, ArrayList<member> participant, ArrayList<String> homework) {
        this.r_name = r_name;
        this.s_name = s_name;
        this.participant = participant;
        this.homework = homework;
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

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public ArrayList<member> getParticipant() {
        return participant;
    }

    public void setParticipant(ArrayList<member> participant) {
        this.participant = participant;
    }

    public ArrayList<String> getHomework() {
        return homework;
    }

    public void setHomework(ArrayList<String> homework) {
        this.homework = homework;
    }
}
