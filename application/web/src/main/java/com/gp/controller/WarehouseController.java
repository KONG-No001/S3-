package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.WarehouseService;
import com.gp.vo.WarehouseVo;
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
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService service;

    @RequestMapping("fenYe.action")
    public Page<WarehouseVo> fenYe(WarehouseVo warehouseVo,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "row", defaultValue = "5") Integer row) {
        return service.fenYe(page, row, warehouseVo);
    }

    //查所有
    @RequestMapping("queryAll.action")
    public List<WarehouseVo> queryAll() {
        return service.list();
    }

    //删
    @RequestMapping("delete.action")
    public boolean delete(Integer id){
        return service.removeById(id);
    }

    //改
    @RequestMapping("update.action")
    public boolean update(WarehouseVo warehouseVo){
        return service.updateById(warehouseVo);
    }
}
