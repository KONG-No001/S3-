package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.DingDanService;
import com.gp.service.GoodsService;
import com.gp.vo.DingDan;
import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    //根据Id查
    @RequestMapping("/queryById.action")
    public DingDan queryById(Integer id) {
        return dingDanService.chaXunByid(id);
    }


}
