package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.PurchaseVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface PurchaseDao extends BaseMapper<PurchaseVo> {
    Page<PurchaseVo> selectPageVo(@Param("pageVo") Page<PurchaseVo> pageVo, @Param("purchaseVo") PurchaseVo purchaseVo);

    //审核
    int sp(@Param("id")int id,@Param("status")int status);
}
