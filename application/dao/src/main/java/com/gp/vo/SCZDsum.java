package com.gp.vo;

import java.util.Date;
import java.util.List;

public class SCZDsum {
    private int sczd_id;
    private Date sczd_time;
    private float sczd_money;
    private List<PurchaseVo> purchaseVoList;

    public int getSczd_id() {
        return sczd_id;
    }

    public void setSczd_id(int sczd_id) {
        this.sczd_id = sczd_id;
    }

    public Date getSczd_time() {
        return sczd_time;
    }

    public void setSczd_time(Date sczd_time) {
        this.sczd_time = sczd_time;
    }

    public float getSczd_money() {
        return sczd_money;
    }

    public void setSczd_money(float sczd_money) {
        this.sczd_money = sczd_money;
    }

    public List<PurchaseVo> getPurchaseVoList() {
        return purchaseVoList;
    }

    public void setPurchaseVoList(List<PurchaseVo> purchaseVoList) {
        this.purchaseVoList = purchaseVoList;
    }

    @Override
    public String toString() {
        return "SCZDsum{" +
                "sczd_id=" + sczd_id +
                ", sczd_time=" + sczd_time +
                ", sczd_money=" + sczd_money +
                ", purchaseVoList=" + purchaseVoList +
                '}';
    }
}
