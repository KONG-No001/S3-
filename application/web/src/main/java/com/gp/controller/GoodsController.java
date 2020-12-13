package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.GoodsService;
import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author weikang
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    //分页
    @RequestMapping("/fenYe.action")
    public Page<GoodsVo> fenYe(GoodsVo goodsVo, Integer page, Integer row) {
        return goodsService.fenYe(page, row, goodsVo);
    }

    //查所有
    @RequestMapping("/queryAll.action")
    public List<GoodsVo> queryAll() {
        return goodsService.list();
    }

    //根据Id查
    @RequestMapping("/queryById.action")
    public GoodsVo queryAll(Integer goodsId) {
        return goodsService.getById(goodsId);
    }

    //增
    @RequestMapping("/add.action")
    public boolean add(GoodsVo goodsVo) {
        return goodsService.save(goodsVo);
    }

    //删
    @RequestMapping("/delete.action")
    public boolean delete(Integer goodsId) {
        return goodsService.removeById(goodsId);
    }

    //增
    @RequestMapping("/update.action")
    public boolean update(GoodsVo goodsVo) {
        return goodsService.updateById(goodsVo);
    }
}
