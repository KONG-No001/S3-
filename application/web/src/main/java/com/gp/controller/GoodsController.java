package com.gp.controller;


import com.gp.service.GoodsService;
import com.gp.vo.GoodsVo;
import com.gp.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
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
    public PageVo<GoodsVo> fenYe(Integer page, Integer row) {
        return goodsService.page(new PageVo<GoodsVo>(page, row));
    }

    //查所有
    @RequestMapping("/queryAll.action")
    public List<GoodsVo> queryAll() {
        return goodsService.list();
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
