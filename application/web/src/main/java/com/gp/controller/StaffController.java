package com.gp.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.service.StaffService;
import com.gp.utils.FileUpdate;
import com.gp.vo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "staff/listStaff.action",produces = "application/json;charset=utf-8")
    @ResponseBody()
    public String listStaff(String type,String value){
        List<Staff> list = staffService.listStaff("staff_"+type,value);
        System.out.println(list);
        String json = JSONObject.toJSONString(list);
        System.out.println(json);
        return json;
    }


    @RequestMapping(value = "staff/update.action",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateStaff(MultipartFile avatarUpdate, String staffUpdate, HttpServletRequest request) throws Throwable {
        Staff staff = new ObjectMapper().readValue(staffUpdate,Staff.class);
        if(avatarUpdate!=null){
            String savePath = "\\assets\\portrait\\staff\\"+staff.getId()+"\\"+avatarUpdate.getOriginalFilename();
            FileUpdate.defaultFileUpdateInIdea(
                    request,
                    (InputStream) avatarUpdate.getInputStream(),
                    savePath
            );
            staff.setPortrait("\\application\\"+savePath);
        }
        return staffService.updateStaff(staff);
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
