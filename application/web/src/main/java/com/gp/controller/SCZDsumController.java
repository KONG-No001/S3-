package com.gp.controller;


import com.gp.service.SCZDsumService;
import com.gp.vo.PageVo;
import com.gp.vo.SCZDsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SCZDsumController {
    @Autowired
    SCZDsumService sczDsumService;
    @RequestMapping(value="/querySCZDsumAll.do")
    @ResponseBody
    @CrossOrigin
    public List<SCZDsum> querySCZDsumAll(SCZDsum sczDsum,
                                         @RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "rows",defaultValue = "5") int rows){
        return sczDsumService.querySCZDsumAll(sczDsum,page,rows);

    }
    @RequestMapping(value="/SCZDsumPageVo.do")
    @ResponseBody
    @CrossOrigin
    public PageVo<SCZDsum> SCZDsumPageVo(SCZDsum sczDsum,
                                         @RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "rows",defaultValue = "5") int rows){
        return sczDsumService.SCZDsumPageVo(sczDsum,page,rows);

    }
    @RequestMapping(value="/querySCZDsumId.do")
    @ResponseBody
    @CrossOrigin
    public SCZDsum querySCZDsumId(int id) {
        return sczDsumService.querySCZDsumId(id);
    }
    @RequestMapping(value="/addSCZDsum.do")
    @ResponseBody
    @CrossOrigin
    public Map addSCZDsum(SCZDsum sczDsum
    ) {
        Map<String,String> map =new HashMap<String,String>();

        int num=sczDsumService.addSCZDsum(sczDsum);
        System.out.println(num);
        if(num==1){
            map.put("msg","添加成功");
        }else {
            map.put("msg","添加失败");
        }

        return map;
    }
    @RequestMapping(value="/deleteSCZDsum.do")
    @ResponseBody  //service.查询方法  得到集合
    @CrossOrigin
    public Map  deleteSCZDsum(int id){
        Map<String,String> map =new HashMap<String,String>();
//        String [] sids=id.split(",");
        int num=0;
//        for(int i=0;i<sids.length;i++){
        num=  sczDsumService.deleteSCZDsum(id);
//        }
        if(num==1){
            map.put("msg","删除成功");
        }else {
            map.put("msg","删除失败");
        }
        return map;
    }
    @RequestMapping(value="/updateSCZDsum.do")
    @ResponseBody
    @CrossOrigin
    public Map updateSCZDsum(SCZDsum sczDsum) {
        Map<String,String> map =new HashMap<String,String>();

        int num=sczDsumService.updateSCZDsum(sczDsum);
        System.out.println(num);
        map.put("msg",num==1?"编辑成功":"编辑失败");
        return map;
    }
}
