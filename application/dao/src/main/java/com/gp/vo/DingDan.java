package com.gp.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
@JsonIgnoreProperties(value = { "handler" })
public class DingDan {
    private Integer id;
    private User user;
    private Shanghu shanghu;
    private Double sum;
    private Integer zt;
    private Integer count;
    @TableField("goods_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private List<DingDanWarehouse> dingDanWarehouses;

    public DingDan(Integer id, User user, Shanghu shanghu, Double sum, Integer zt, Integer count, Date time, List<DingDanWarehouse> dingDanWarehouses) {
        this.id = id;
        this.user = user;
        this.shanghu = shanghu;
        this.sum = sum;
        this.zt = zt;
        this.count = count;
        this.time = time;
        this.dingDanWarehouses = dingDanWarehouses;
    }

    public DingDan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shanghu getShanghu() {
        return shanghu;
    }

    public void setShanghu(Shanghu shanghu) {
        this.shanghu = shanghu;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DingDanWarehouse> getDingDanWarehouses() {
        return dingDanWarehouses;
    }

    public void setDingDanWarehouses(List<DingDanWarehouse> dingDanWarehouses) {
        this.dingDanWarehouses = dingDanWarehouses;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DingDan{" +
                "id=" + id +
                ", user=" + user +
                ", shanghu=" + shanghu +
                ", sum=" + sum +
                ", zt=" + zt +
                ", count=" + count +
                ", time=" + time +
                ", dingDanWarehouses=" + dingDanWarehouses +
                '}';
    }
}
