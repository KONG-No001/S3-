package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface WarehouseDao extends BaseMapper<WarehouseVo> {
    Page<WarehouseVo> selectPageVo(@Param("pageVo") Page<WarehouseVo> pageVo, @Param("warehouseVo") WarehouseVo warehouseVo);

    WarehouseVo queryByGoodsId(int id);
}
