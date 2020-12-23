package com.gp.controller;

import com.alibaba.fastjson.JSONObject;
import com.gp.service.StaffService;
import com.gp.vo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "staff/listStaff.action",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String listUesr(String type,String value){
        return JSONObject.toJSONString(staffService.listStaff("staff_"+type,value)) ;
    }


    @RequestMapping(value = "staff/update.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateStaff(@RequestBody Staff data){
        return staffService.updateStaff(data);
    }

    @RequestMapping(value = "staff/add.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean addStaff(@RequestBody Staff data){
        return staffService.addStaff(data);
    }

    @RequestMapping(value = "staff/remove.action",method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteStaff(int id){
        return staffService.deleteStaff(id);
    }

    @RequestMapping(value = "staff/updateRoleRelation.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateRoleRelation(@RequestBody Map<String,Object> params){
        return staffService.updateRoleRelation((int)params.get("staffId"),(List<Integer>) params.get("roleIds")) ;
    }

}
