package com.gp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.PurchaseService;
import com.gp.vo.PurchaseGoodsVo;
import com.gp.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
//        System.out.println(data);
//        System.out.println(data.get("form"));
//        System.out.println(data.get("goodsIds"));
//        PurchaseGoodsVo purchaseGoodsVo=new PurchaseGoodsVo();
//        for (int i = 0; i < data.get("goodsIds").; i++) {
//
//        }
//        purchaseGoodsVo.setGoodsId(data.get("goodsIds"));
        //ArrayList<PurchaseGoodsVo> purchaseGoodsVoList = (ArrayList<PurchaseGoodsVo>)data.get("goodsIds");
//        PurchaseVo purchaseVo = new PurchaseVo();


//        return false;

        return service.add(JSON.parseObject(JSON.toJSONString(data.get("form")),new TypeReference<PurchaseVo>(){}), (ArrayList<PurchaseGoodsVo>)data.get("goodsIds"))>0;
    }

    //审批
    /*@RequestMapping("sp.action")
    public boolean sp(Integer id, Integer status) {
        return service.sp(id, status) > 0;
    }*/

    //删
    @RequestMapping("delete.action")
    public boolean delete(Integer id,Integer purchaseId){
        return service.delete(id,purchaseId)>0;
    }

    //改
    @RequestMapping("update.action")
    public boolean update(PurchaseVo purchaseVo){
        return service.updateById(purchaseVo);
    }


}
