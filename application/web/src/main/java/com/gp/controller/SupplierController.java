package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.SupplierService;
import com.gp.vo.SupplierVo;
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
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    //分页
    @RequestMapping("/fenYe.action")
    public Page<SupplierVo> fenYe(SupplierVo supplierVo, Integer page, Integer row) {
        return supplierService.fenYe(page, row, supplierVo);
    }

    //查所有
    @RequestMapping("/queryAll.action")
    public List<SupplierVo> queryAll() {
        return supplierService.list();
    }

    //根据Id查
    @RequestMapping("/queryById.action")
    public SupplierVo queryAll(Integer id) {
        return supplierService.getById(id);
    }

    //增
    @RequestMapping("/add.action")
    public boolean add(SupplierVo supplierVo) {
        return supplierService.save(supplierVo);
    }

    //删
    @RequestMapping("/delete.action")
    public boolean delete(Integer id) {
        return supplierService.removeById(id);
    }

    //批量删除
    @RequestMapping("/deleteByIds.action")
    public boolean deleteByIds( @RequestParam(value = "ids") List<Integer> ids) {
        return supplierService.removeByIds(ids);
    }

    //增
    @RequestMapping("/update.action")
    public boolean update(SupplierVo supplierVo) {
        return supplierService.updateById(supplierVo);
    }
}
