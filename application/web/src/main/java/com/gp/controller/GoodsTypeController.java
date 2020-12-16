package com.gp.controller;


import com.gp.service.GoodsTypeService;
import com.gp.vo.GoodsTypeVo;
//import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    GoodsTypeService goodsTypeService;

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
    public boolean delete(Integer goodsId) {
        return goodsTypeService.removeById(goodsId);
    }

    //增
    @RequestMapping("/update.action")
    public boolean update(GoodsTypeVo goodsVo) {
        return goodsTypeService.updateById(goodsVo);
    }
}
