package com.gp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
@JsonIgnoreProperties(value = { "handler" })
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_type")
public class GoodsTypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_type_id", type = IdType.AUTO)
    private Integer goodsTypeId;

    @TableField("goods_type_name")
    private String goodsTypeName;

    @TableField("goods_type_remark")
    private String goodsTypeRemark;

}
