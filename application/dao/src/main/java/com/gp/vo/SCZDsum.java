package com.gp.vo;

public class SCZDsum {
    private int sczd_id;
    private float sczd_money;

    public int getSczd_id() {
        return sczd_id;
    }

    public void setSczd_id(int sczd_id) {
        this.sczd_id = sczd_id;
    }

    public float getSczd_money() {
        return sczd_money;
    }

    public void setSczd_money(float sczd_money) {
        this.sczd_money = sczd_money;
    }

    @Override
    public String toString() {
        return "SCZDsum{" +
                "sczd_id=" + sczd_id +
                ", sczd_money=" + sczd_money +
                '}';
    }
}
