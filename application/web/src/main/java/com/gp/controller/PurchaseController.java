package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.PurchaseService;
import com.gp.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public boolean add(PurchaseVo purchaseVo, @RequestParam(value = "goodsIds") List<Integer> goodsIds) {
        return service.add(purchaseVo, goodsIds)>0;
    }

    //审批
    @RequestMapping("sp.action")
    public boolean sp(Integer id, Integer status) {
        return service.sp(id, status) > 0;
    }

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
