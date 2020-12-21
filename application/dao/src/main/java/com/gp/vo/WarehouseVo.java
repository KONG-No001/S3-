package com.gp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode(callSuper = false)
@TableName("warehouse")
public class WarehouseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
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

    @TableField("status")
    private Integer status;

    @TableField(exist=false)
    private GoodsVo goodsVo;

}
