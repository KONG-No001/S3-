package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.PurchaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface PurchaseDao extends BaseMapper<PurchaseVo> {
   //查所有
    List<PurchaseVo> queryAll();

    Page<PurchaseVo> selectPageVo(@Param("pageVo") Page<PurchaseVo> pageVo, @Param("purchaseVo") PurchaseVo purchaseVo);
}
