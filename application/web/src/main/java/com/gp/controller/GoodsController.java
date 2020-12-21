package com.gp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.service.GoodsService;
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
                       HttpServletRequest request) throws IOException {
        goodsVo.setGoodsImg("/application/assets/"+img.getOriginalFilename());
        goodsVo.setGoodsImg2("/application/assets/"+img2.getOriginalFilename());
        goodsVo.setGoodsImg3("/application/assets/"+img3.getOriginalFilename());
        goodsVo.setGoodsImg4("/application/assets/"+img4.getOriginalFilename());

        //将文件持久化 保存到 服务端 本地磁盘（项目发布的路径）
        //目发布的路径
        String path = request.getServletContext().getRealPath("/assets");
        System.out.println(path);
        File file =new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //将文件持久化 保存到 服务端
        img.transferTo(new File(path,img.getOriginalFilename()));
        img2.transferTo(new File(path,img2.getOriginalFilename()));
        img3.transferTo(new File(path,img3.getOriginalFilename()));
        img4.transferTo(new File(path,img4.getOriginalFilename()));


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
                          HttpServletRequest request) throws IOException {
        goodsVo.setGoodsImg("/application/assets/"+img.getOriginalFilename());
        goodsVo.setGoodsImg2("/application/assets/"+img2.getOriginalFilename());
        goodsVo.setGoodsImg3("/application/assets/"+img3.getOriginalFilename());
        goodsVo.setGoodsImg4("/application/assets/"+img4.getOriginalFilename());
        //将文件持久化 保存到 服务端 本地磁盘（项目发布的路径）
        //目发布的路径
        String path = request.getServletContext().getRealPath("/assets");
        System.out.println(path);
        File file =new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //将文件持久化 保存到 服务端
        img.transferTo(new File(path,img.getOriginalFilename()));
        img2.transferTo(new File(path,img2.getOriginalFilename()));
        img3.transferTo(new File(path,img3.getOriginalFilename()));
        img4.transferTo(new File(path,img4.getOriginalFilename()));

        return goodsService.updateById(goodsVo);
    }
}
