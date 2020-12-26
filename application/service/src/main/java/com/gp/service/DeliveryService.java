package com.gp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.vo.DeliveryVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2020-12-24
 */
public interface DeliveryService extends IService<DeliveryVo> {
    Page<DeliveryVo> fenYe(DeliveryVo deliveryVo,int page,int row);

    //发货
    int fh(int id);

    //已收货
    int sh(int id);

}
