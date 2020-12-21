package com.gp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("supplier")
public class SupplierVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supplier_id", type = IdType.AUTO)
    private Integer supplierId;

    @TableField("supplier_text")
    private String supplierText;

    @TableField("supplier_name")
    private String supplierName;

    @TableField("supplier_tel")
    private String supplierTel;

    @TableField("supplier_address")
    private String supplierAddress;


}
