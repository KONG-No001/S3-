package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.SupplierVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
public interface SupplierDao extends BaseMapper<SupplierVo> {
    Page<SupplierVo> selectPageVo(@Param("pageVo") Page<SupplierVo> pageVo, @Param("supplierVo") SupplierVo supplierVo);
}
