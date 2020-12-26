package com.gp.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "handler" })
@TableName(value = "delivery",resultMap = "DeliveryMap")
public class DeliveryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("dingdan_id")
    private Integer dingdanId;

    @TableField("user_id")
    private Integer userId;

    @TableField(exist=false)
    private String name;

    @TableField("shop_id")
    private Integer shopId;

    @TableField("time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;

    @TableField("sum")
    private Double sum;

    @TableField("status")
    private Integer status;

    @TableField(exist=false)
    private DingDan dingDan;

}
