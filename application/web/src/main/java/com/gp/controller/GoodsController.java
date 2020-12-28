package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.GoodsService;
import com.gp.utils.FileUpdate;
import com.gp.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public boolean add(GoodsVo goodsVo,
                       @RequestParam("img") MultipartFile img,
                       @RequestParam("img2") MultipartFile img2,
                       @RequestParam("img3") MultipartFile img3,
                       @RequestParam("img4") MultipartFile img4,
                       HttpServletRequest request) throws Throwable {

        String[] paths = FileUpdate.getLocalPathAndServletPath(
                request,null,"/src/main/webapp/assets/","/assets/");


        MultipartFile[] imgs = new MultipartFile[]{
                img,img2,img3,img4
        };

        for (MultipartFile file: imgs ) {
            FileUpdate.executeUpdate(
                    (FileInputStream)file.getInputStream(),
                    paths[0]+file.getOriginalFilename(),
                    paths[1]+file.getOriginalFilename());
        }

        goodsVo.setGoodsImg("/application/assets/"+img.getOriginalFilename());
        goodsVo.setGoodsImg2("/application/assets/"+img2.getOriginalFilename());
        goodsVo.setGoodsImg3("/application/assets/"+img3.getOriginalFilename());
        goodsVo.setGoodsImg4("/application/assets/"+img4.getOriginalFilename());

        return goodsService.save(goodsVo);
    }

    //删
    @RequestMapping("/delete.action")
    public boolean delete(Integer goodsId) {
        return goodsService.removeById(goodsId);
    }

    //批量删除
    @RequestMapping("/deleteByIds.action")
    public boolean deleteByIds( @RequestParam(value = "goodsIds") List<Integer> goodsIds) {
        return goodsService.removeByIds(goodsIds);
    }

    //改
    @RequestMapping("/update.action")
    public boolean update(GoodsVo goodsVo,
                          @RequestParam("img") MultipartFile img,
                          @RequestParam("img2") MultipartFile img2,
                          @RequestParam("img3") MultipartFile img3,
                          @RequestParam("img4") MultipartFile img4,
                          HttpServletRequest request, HttpServletResponse response) throws Throwable {

        goodsVo.setGoodsImg("/application/assets/"+img.getOriginalFilename());
        goodsVo.setGoodsImg2("/application/assets/"+img2.getOriginalFilename());
        goodsVo.setGoodsImg3("/application/assets/"+img3.getOriginalFilename());
        goodsVo.setGoodsImg4("/application/assets/"+img4.getOriginalFilename());

        String[] paths = FileUpdate.getLocalPathAndServletPath(
                request,null,"/src/main/webapp/assets/","/assets/");


        MultipartFile[] imgs = new MultipartFile[]{
                img,img2,img3,img4
        };

        for (MultipartFile file: imgs ) {
            FileUpdate.executeUpdate(
                    (FileInputStream)file.getInputStream(),
                    paths[0]+file.getOriginalFilename(),
                    paths[1]+file.getOriginalFilename());
        }



        goodsVo.setGoodsImg("/application/assets/"+img.getOriginalFilename());
        goodsVo.setGoodsImg2("/application/assets/"+img2.getOriginalFilename());
        goodsVo.setGoodsImg3("/application/assets/"+img3.getOriginalFilename());
        goodsVo.setGoodsImg4("/application/assets/"+img4.getOriginalFilename());

        return goodsService.updateById(goodsVo);
    }

}
