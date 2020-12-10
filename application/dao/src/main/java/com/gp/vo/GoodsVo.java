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
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
//不需要手动触发加载延迟属性，当延迟属性被调用时自动创建Session进行获取。可见如果有多个延迟属性需要被使用时，会造成多次创建Session，此时可以标注为AutoLazy(false)或不标注，然后采用initialze方法手动一次性加载需要的属性
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "handler" })
@TableName(value = "goods",resultMap = "GoodsMap")
public class GoodsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @TableField("goods_code")
    private String goodsCode;

    @TableField("goods_name")
    private String goodsName;

    @TableField("goods_brand")
    private String goodsBrand;

    @TableField("goods_type_id")
    private Integer goodsTypeId;

    @TableField("goods_guige")
    private String goodsGuige;

    @TableField("goods_in_price")
    private Double goodsInPrice;

    @TableField("goods_img")
    private String goodsImg;

    @TableField("goods_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date goodsTime;

    @TableField("goods_factory")
    private String goodsFactory;

    @TableField("goods_address")
    private String goodsAddress;

    @TableField("goods_status")
    private Integer goodsStatus;

    private GoodsTypeVo goodsTypeVo;
}
