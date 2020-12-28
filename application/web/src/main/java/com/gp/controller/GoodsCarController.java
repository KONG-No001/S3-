package com.gp.controller;


import com.gp.service.DingDanService;
import com.gp.service.GoodsCarService;
import com.gp.service.GoodsService;
import com.gp.vo.DingDan;
import com.gp.vo.GoodsCar;
import com.gp.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/goodscar")
public class GoodsCarController {
    @Autowired
    GoodsCarService goodsCarService;

    //根据Id查
    @RequestMapping("/queryByUid.action")
    public List<GoodsCar> queryByUid(Integer uid) {
        return goodsCarService.chaXunByTJ(uid);
    }

    //添加购物车
    @RequestMapping("/addGoodsCar.action")
    public String addGoodsCar(GoodsCar goodsCar) {
        int sl=goodsCar.getShu();
        int num=0;
        GoodsCar goodsCar2=goodsCarService.chaXunBy(goodsCar.getUid(),goodsCar.getWarehouseVo().getId());
        System.out.println(goodsCar);
        if(goodsCar2==null){
             num = goodsCarService.tianJia(goodsCar);
        }else {
            num=goodsCarService.xiuGai(sl+goodsCar2.getShu(),goodsCar2.getId());
        }

        if(num==1){
            return "添加成功";
        }

        return "添加失败";
    }

    //修改购物车数量
    @RequestMapping("/updateGoodsCar.action")
    public String updateGoodsCar(Integer id,Integer sl) {
        int num = goodsCarService.xiuGai(sl,id);
        if(num==1){
            return "修改成功";
        }

        return "修改失败";
    }

    //删除购物车商品
    @RequestMapping("delGoodsCar.action")
    public String delGoodsCar(Integer id) {
        int num = goodsCarService.shanChu(id);
        if(num==1){
            return "删除成功";
        }

        return "删除失败";
    }
}
