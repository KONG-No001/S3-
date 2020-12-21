package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.WarehouseVo;
import com.gp.dao.WarehouseDao;
import com.gp.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, WarehouseVo> implements WarehouseService {

    @Autowired
    WarehouseDao warehouseDao;
    @Override
    public Page<WarehouseVo> fenYe(int page, int row, WarehouseVo warehouseVo) {
        Page<WarehouseVo> pageVo=new Page<>(page,row);

        return warehouseDao.selectPageVo(pageVo,warehouseVo);
    }
}
