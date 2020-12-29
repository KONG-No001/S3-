package com.gp.controller;


import com.gp.service.DingDanService;
import com.gp.service.GoodsCarService;
import com.gp.vo.DingDan;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    //付款成功

    @RequestMapping("/fk.action")
    @CrossOrigin
    public void fk(Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        dingDanService.fk(id);
        response.sendRedirect("/application/fk.jsp");
    }


    //收货
    @RequestMapping("/sh.action")
    public boolean sh(Integer id){
        return dingDanService.sh(id)>0;
    }


    //添加订单
    @RequestMapping("/addDingDan.action")
    public Integer addDingDan(DingDan dingDan, @Param(value = "wid") String wid, @Param(value = "shu") String shu) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        dingDan.setTime(df.parse(df.format(new Date())));

        Integer did = dingDanService.addDingDan(dingDan);
        if (wid.contains(",") && shu.contains(",")) {
            String[] wids = wid.split(",");
            String[] shus = shu.split(",");
            for (int i = 0; i < wids.length; i++) {
                dingDanService.addWarehouse(did, Integer.valueOf(wids[i]), Integer.valueOf(shus[i]));
                goodsCarService.shanChuDingDan(Integer.valueOf(wids[i]), dingDan.getUser().getId());
            }
        } else {
            dingDanService.addWarehouse(did, Integer.valueOf(wid), Integer.valueOf(shu));

        }
        return did;
    }


    //根据状态查
    @RequestMapping("/queryByZt.action")
    public List<DingDan> queryByZt(Integer zt, Integer uid) {
        return dingDanService.chaXunByzt(zt, uid);
    }

    //根据状态查
    @RequestMapping("/daifukuan.action")
    public List<DingDan> daifukuan(Integer zt) {
        return dingDanService.daifukuan(zt);
    }

    @RequestMapping("/daifahuo.action")
    public List<DingDan> daifahuo(Integer zt) {
        return dingDanService.daifahuo(zt);
    }

    @RequestMapping("/daitihuo.action")
    public List<DingDan> daitihuo(Integer zt) {
        return dingDanService.daitihuo(zt);
    }

    @RequestMapping("/yitihuo.action")
    public List<DingDan> yitihuo(Integer zt) {
        return dingDanService.yitihuo(zt);
    }
}
