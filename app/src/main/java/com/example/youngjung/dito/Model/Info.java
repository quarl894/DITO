package com.example.youngjung.dito.Model;

import android.widget.ImageView;

import java.io.Serializable;
import java.net.URI;

public class Info implements Serializable{
    private static final long serialVersionUID2 = 1L;
    private String r_name;
    private String s_name;
    private String cnt;
    private int img_master;
    private String img1;
    private String img2;
    private String img3;

    public Info() {
    }

    public Info(String r_name, String s_name, String cnt, int img_master, String img1, String img2, String img3) {
        this.r_name = r_name;
        this.s_name = s_name;
        this.cnt = cnt;
        this.img_master = img_master;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public void setImg_master(int img_master) {
        this.img_master = img_master;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getR_name() {
        return r_name;
    }

    public String getS_name() {
        return s_name;
    }

    public String getCnt() {
        return cnt;
    }

    public int getImg_master() {
        return img_master;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public String getImg3() {
        return img3;
    }
}
