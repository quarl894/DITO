package com.example.youngjung.dito.Model;

import android.widget.ImageView;

public class Info {
    private String r_name;
    private String s_name;
    private String cnt;
    private int img_master;
    private int img1;
    private int img2;
    private int img3;

    public Info(String r_name, String s_name, String cnt, int img_master, int img1, int img2, int img3) {
        this.r_name = r_name;
        this.s_name = s_name;
        this.cnt = cnt;
        this.img_master = img_master;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
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

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public int getImg_master() {
        return img_master;
    }

    public void setImg_master(int img_master) {
        this.img_master = img_master;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public int getImg3() {
        return img3;
    }

    public void setImg3(int img3) {
        this.img3 = img3;
    }
}
