package com.gp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.WarehouseVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
public interface WarehouseService extends IService<WarehouseVo> {
        Page<WarehouseVo> fenYe(int page,int row,WarehouseVo warehouseVo);
}
