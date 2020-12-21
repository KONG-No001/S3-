package com.gp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gp.vo.SupplierVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
public interface SupplierService extends IService<SupplierVo> {
    Page<SupplierVo> fenYe(Integer page, Integer row, SupplierVo supplierVo);
}
