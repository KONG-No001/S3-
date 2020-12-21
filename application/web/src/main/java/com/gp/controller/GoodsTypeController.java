package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.GoodsTypeService;
import com.gp.vo.GoodsTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    GoodsTypeService goodsTypeService;

    //分页
    @RequestMapping("/fenYe.action")
    public Page<GoodsTypeVo> fenYe(GoodsTypeVo goodsTypeVo, Integer page, Integer row) {
        return goodsTypeService.fenYe(page, row, goodsTypeVo);
    }

    //查所有
    @RequestMapping("/queryAll.action")
    public List<GoodsTypeVo> queryAll() {
        return goodsTypeService.list();
    }

    //根据Id查
    @RequestMapping("/queryById.action")
    public GoodsTypeVo queryAll(Integer goodsTypeId) {
        return goodsTypeService.getById(goodsTypeId);
    }

    //增
    @RequestMapping("/add.action")
    public boolean add(GoodsTypeVo goodsTypeVo) {
        return goodsTypeService.save(goodsTypeVo);
    }

    //删
    @RequestMapping("/delete.action")
    public boolean delete(Integer goodsTypeId) {
        return goodsTypeService.removeById(goodsTypeId);
    }

    //批量删除
    @RequestMapping("/deleteByIds.action")
    public boolean deleteByIds( @RequestParam(value = "goodsTypeIds") List<Integer> goodsTypeIds) {
        return goodsTypeService.removeByIds(goodsTypeIds);
    }

    //增
    @RequestMapping("/update.action")
    public boolean update(GoodsTypeVo goodsTypeVo) {
        return goodsTypeService.updateById(goodsTypeVo);
    }
}
