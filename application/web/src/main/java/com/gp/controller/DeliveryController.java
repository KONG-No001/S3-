package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.DeliveryService;
import com.gp.vo.DeliveryVo;
import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weikang
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    //分页
    @RequestMapping("/fenYe.action")
    public Page<DeliveryVo> fenYe(DeliveryVo deliveryVo, Integer page, Integer row) {
        return deliveryService.fenYe(deliveryVo,page, row);
    }

    //发货
    @RequestMapping("/fh.action")
    public boolean fh(Integer id){
        return deliveryService.fh(id)>0;
    }

}
