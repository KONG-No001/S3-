package com.gp.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("delivery")
public class DeliveryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("dingdan_id")
    private Integer dingdanId;

    @TableField("user_id")
    private Integer userId;

    @TableField("shop_id")
    private Integer shopId;

    @TableField("time")
    private LocalDateTime time;

    @TableField("sum")
    private Double sum;

    @TableField("status")
    private Integer status;


}
