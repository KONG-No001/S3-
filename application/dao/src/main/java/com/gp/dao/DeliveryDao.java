package com.gp.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.DeliveryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-24
 */
public interface DeliveryDao extends BaseMapper<DeliveryVo> {
    Page<DeliveryVo> selectPageVo(@Param("pageVo") Page<DeliveryVo> pageVo, @Param("deliveryVo") DeliveryVo deliveryVo);

    //发货
    int fh(int id);

    //已收货
    int sh(int id);

}
