package com.gp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.PurchaseService;
import com.gp.vo.PurchaseGoodsVo;
import com.gp.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weikang
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService service;

    @RequestMapping("fenYe.action")
    public Page<PurchaseVo> fenYe(PurchaseVo purchaseVo,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "row", defaultValue = "5") Integer row) {
        return service.fenYe(page, row, purchaseVo);
    }

    //查所有
    @RequestMapping("queryAll.action")
    public List<PurchaseVo> queryAll() {
        return service.list();
    }

    //增
    @RequestMapping("add.action")
    public boolean add(@RequestBody Map<String, Object> data) {
        return service.add(JSON.parseObject(JSON.toJSONString(data.get("form")),new TypeReference<PurchaseVo>(){}), (ArrayList<PurchaseGoodsVo>)data.get("goodsIds"))>0;
    }

    //根据订单Id查询所有商品
    @RequestMapping("queryByPurchaseId.action")
    public List<PurchaseGoodsVo> queryByPurchaseId(Integer id) {
        return service.queryByPurchaseId(id);
    }

}
