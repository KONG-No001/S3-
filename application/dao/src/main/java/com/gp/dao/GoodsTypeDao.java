package com.gp.dao;

import com.gp.vo.GoodsTypeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
public interface GoodsTypeDao extends BaseMapper<GoodsTypeVo> {
    GoodsTypeVo queryById(int id);
}
