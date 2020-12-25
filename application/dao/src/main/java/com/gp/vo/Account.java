package com.gp.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Account {
    private Integer id;

    private Integer purchaseId;//采购单ID

    private Integer orderId;//销售单ID

    private Double total;//订单金额

    private Integer accountType;//订单类型 0代表采购 1代表销售

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time; //订单时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime; //查询开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//查询结束时间

    private PurchaseVo purchaseVo;

    private DingDan dingDan;

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PurchaseVo getPurchaseVo() {
        return purchaseVo;
    }

    public void setPurchaseVo(PurchaseVo purchaseVo) {
        this.purchaseVo = purchaseVo;
    }

    public DingDan getDingDan() {
        return dingDan;
    }

    public void setDingDan(DingDan dingDan) {
        this.dingDan = dingDan;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", purchaseId=" + purchaseId +
                ", orderId=" + orderId +
                ", total=" + total +
                ", accountType=" + accountType +
                ", time=" + time +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", purchaseVo=" + purchaseVo +
                ", dingDan=" + dingDan +
                '}';
    }
}
