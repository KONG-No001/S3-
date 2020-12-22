package com.gp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
@Data
@JsonIgnoreProperties(value = { "handler" })
@EqualsAndHashCode(callSuper = false)
@TableName("warehouse")
public class WarehouseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("goods_id")
    private Integer goodsId;

    @TableField("name")
    private String name;

    @TableField("goods_type_id")
    private Integer goodsTypeId;

    @TableField("goods_out_price")
    private Double goodsOutPrice;

    @TableField("count")
    private Integer count;

    @TableField(exist=false)
    private GoodsVo goodsVo;

    @TableField(exist=false)
    private GoodsTypeVo goodsTypeVo;

}
