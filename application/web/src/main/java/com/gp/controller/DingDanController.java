package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.DingDanService;
import com.gp.service.GoodsCarService;
import com.gp.service.GoodsService;
import com.gp.vo.DingDan;
import com.gp.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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
@RequestMapping("/dingdan")
public class DingDanController {
    @Autowired
    DingDanService dingDanService;
    @Autowired
    GoodsCarService goodsCarService;

    //根据Id查
    @RequestMapping("/queryById.action")
    public DingDan queryById(Integer id) {
        return dingDanService.chaXunByid(id);
    }

    //添加订单
    @RequestMapping("/addDingDan.action")
    public Integer addDingDan(DingDan dingDan, @Param(value = "wid") String wid, @Param(value = "shu") String shu) {
        Date date=new Date();
        dingDan.setTime(date);
        Integer did=dingDanService.addDingDan(dingDan);
        if(wid.contains(",")&&shu.contains(",")){
            String[] wids=wid.split(",");
            String[] shus=shu.split(",");
            for(int i=0;i<wids.length;i++){
                dingDanService.addWarehouse(did,Integer.valueOf(wids[i]),Integer.valueOf(shus[i]));
                goodsCarService.shanChuDingDan(Integer.valueOf(wids[i]),dingDan.getUser().getId());
            }
        }else{
            dingDanService.addWarehouse(did,Integer.valueOf(wid),Integer.valueOf(shu));

        }
        return did;
    }

}
