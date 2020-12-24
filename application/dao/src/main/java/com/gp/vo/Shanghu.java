package com.gp.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Shanghu {
    private int sh_id;
    private String sh_name;
    private String sh_phone;
    private String sh_image;
    private String sh_address;


    private Date sh_createtime;


    public String getSh_image() {
        return sh_image;
    }

    public void setSh_image(String sh_image) {
        this.sh_image = sh_image;
    }

    public int getSh_id() {
        return sh_id;
    }

    public void setSh_id(int sh_id) {
        this.sh_id = sh_id;
    }

    public String getSh_name() {
        return sh_name;
    }

    public void setSh_name(String sh_name) {
        this.sh_name = sh_name;
    }

    public String getSh_phone() {
        return sh_phone;
    }

    public void setSh_phone(String sh_phone) {
        this.sh_phone = sh_phone;
    }

    public String getSh_address() {
        return sh_address;
    }

    public void setSh_address(String sh_address) {
        this.sh_address = sh_address;
    }

    public Date getSh_creattime() {
        return sh_createtime;
    }

    public void setSh_creattime(Date sh_creattime) {
        this.sh_createtime = sh_creattime;
    }

    @Override
    public String toString() {
        return "Shanghu{" +
                "sh_id=" + sh_id +
                ", sh_name='" + sh_name + '\'' +
                ", sh_phone='" + sh_phone + '\'' +
                ", sh_image='" + sh_image + '\'' +
                ", sh_address='" + sh_address + '\'' +
                ", sh_creattime=" + sh_createtime +
                '}';
    }
}
