package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
public interface GoodsDao extends BaseMapper<GoodsVo> {

    Page<GoodsVo> selectPageVo(@Param("pageVo") Page<GoodsVo> pageVo, @Param("goodsVo") GoodsVo goodsVo);
}
