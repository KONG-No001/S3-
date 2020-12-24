package com.gp.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Shsum {
    private int shsum_id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date shsum_time;
    private float shsum_money;

    public int getShsum_id() {
        return shsum_id;
    }

    public void setShsum_id(int shsum_id) {
        this.shsum_id = shsum_id;
    }

    public Date getShsum_time() {
        return shsum_time;
    }

    public void setShsum_time(Date shsum_time) {
        this.shsum_time = shsum_time;
    }

    public float getShsum_money() {
        return shsum_money;
    }

    public void setShsum_money(float shsum_money) {
        this.shsum_money = shsum_money;
    }

    @Override
    public String toString() {
        return "Shsum{" +
                "shsum_id=" + shsum_id +
                ", shsum_time=" + shsum_time +
                ", shsum_money=" + shsum_money +
                '}';
    }
}
