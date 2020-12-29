package com.gp.controller;


import com.gp.service.ShanghuService;
import com.gp.utils.FileUpdate;
import com.gp.vo.PageVo;
import com.gp.vo.Shanghu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShanghuController {
    @Autowired
    ShanghuService shanghuService;
    @RequestMapping(value="/queryShanghuAll.do")
    @ResponseBody
    @CrossOrigin
    public List<Shanghu> queryShanghuAll(Shanghu shanghu,
                                             @RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "rows",defaultValue = "5") int rows){
        return shanghuService.queryShanghuAll(shanghu,page,rows);

    }
    @RequestMapping(value="/ShanghuPageVo.do")
    @ResponseBody
    @CrossOrigin
    public PageVo<Shanghu> ShanghuPageVo(Shanghu shanghu,
                                       @RequestParam(value = "page",defaultValue = "1") int page,
                                       @RequestParam(value = "rows",defaultValue = "5") int rows){
        PageVo<Shanghu> pageVo = shanghuService.ShanghuPageVo(shanghu,page,rows);

        return pageVo;

    }
    @RequestMapping(value="/queryShanghuId.do")
    @ResponseBody
    @CrossOrigin
    public Shanghu queryShanghuId(int id) {
        return shanghuService.queryShanghuId(id);
    }
    @RequestMapping(value="/addShanghu.do")
    @ResponseBody
    @CrossOrigin
    public Map addShanghu(Shanghu shanghu,@RequestParam("img")MultipartFile img,
                          HttpServletRequest request) throws Throwable {
        Map<String,String> map =new HashMap<String,String>();
        shanghu.setImage("/application/assets/"+img.getOriginalFilename());

        String[] paths = FileUpdate.getLocalPathAndServletPath(
                request,null,"/src/main/webapp/assets/","/assets/");
        MultipartFile[] imgs = new MultipartFile[]{
                img
        };
        for (MultipartFile file: imgs ) {
            FileUpdate.executeUpdate(
                    file.getInputStream(),
                    paths[0]+file.getOriginalFilename(),
                    paths[1]+file.getOriginalFilename());
        }
        shanghu.setImage("application/assets/"+img.getOriginalFilename());
        int num=  shanghuService.addShanghu(shanghu);
        map.put("msg",num==1?"添加成功":"添加失败");
        return map;

    }
    @RequestMapping(value="/deleteShanghu.do")
    @ResponseBody  //service.查询方法  得到集合
    @CrossOrigin
    public Map  deleteShanghu(Integer id){
        Map<String,String> map =new HashMap<String,String>();
//        String [] sids=id.split(",");
        int num=0;
//        for(int i=0;i<sids.length;i++){
            num=  shanghuService.deleteShanghu(id);
//        }
        if(num==1){
            map.put("msg","删除成功");
        }else {
            map.put("msg","删除失败");
        }
        return map;
    }
    @RequestMapping(value="/updateShanghu.do")
    @ResponseBody
    @CrossOrigin
    public Map updateShanghu(Shanghu shanghu, @RequestParam("img") MultipartFile img,
                             HttpServletRequest request) throws Throwable {
        Map<String,String> map =new HashMap<String,String>();
        shanghu.setImage("/application/assets/"+img.getOriginalFilename());
        String[] paths=FileUpdate.getLocalPathAndServletPath(
                request,null,"/src/main/webapp/assets/","/assets/");
        MultipartFile[] imgs=new MultipartFile[] {
                img
        };
        for(MultipartFile file:imgs){
            FileUpdate.executeUpdate(
                    file.getInputStream(),
                    paths[0]+file.getOriginalFilename(),
                    paths[1]+file.getOriginalFilename()
            );
        }
        shanghu.setImage("application/assets/"+img.getOriginalFilename());
        int num=  shanghuService.updateShanghu(shanghu);
        map.put("msg",num==1?"修改成功":"修改失败");
        return map;
    }

}
