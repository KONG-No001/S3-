package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.DeliveryVo;
import com.gp.dao.DeliveryDao;
import com.gp.service.DeliveryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2020-12-24
 */
@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryDao, DeliveryVo> implements DeliveryService {
    @Autowired
    DeliveryDao deliveryDao;

    @Override
    public Page<DeliveryVo> fenYe(DeliveryVo deliveryVo, int page, int row) {
        Page<DeliveryVo> voPage=new Page<>(page,row);

        return deliveryDao.selectPageVo(voPage,deliveryVo);
    }
}
