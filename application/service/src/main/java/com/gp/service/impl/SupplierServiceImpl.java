package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gp.dao.SupplierDao;
import com.gp.service.SupplierService;
import com.gp.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2020-12-15
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, SupplierVo> implements SupplierService {

    @Autowired
    SupplierDao supplierDao;

    @Override
    public Page<SupplierVo> fenYe(Integer page, Integer row, SupplierVo supplierVo) {
        //初始化page
        Page<SupplierVo> pageVo = new Page<>(page,row);

        return  supplierDao.selectPageVo(pageVo,supplierVo);
    }
}
