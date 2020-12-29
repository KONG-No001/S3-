package com.gp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.DingDanService;
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
    @Autowired
    DingDanService dingDanService;

    @Override
    public Page<DeliveryVo> fenYe(DeliveryVo deliveryVo, int page, int row) {
        Page<DeliveryVo> voPage=new Page<>(page,row);

        return deliveryDao.selectPageVo(voPage,deliveryVo);
    }

    @Override
    public int fh(int id) {
        DeliveryVo deliveryVo=deliveryDao.selectById(id);
        dingDanService.fh(deliveryVo.getDingdanId());
        return deliveryDao.fh(id);
    }

    @Override
    public int sh(int id) {
        return deliveryDao.sh(id);
    }

}
