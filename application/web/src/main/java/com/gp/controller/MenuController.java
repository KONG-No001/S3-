package com.gp.controller;

import com.gp.service.MenuService;
import com.gp.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/listMenu.action")
    @ResponseBody
    public List<Menu> listMenu(int id){
        return menuService.listMenu(id);
    }


    @RequestMapping("/listRoutes.action")
    @ResponseBody
    public List<Map<String,Object>> listRoutes(){
        return menuService.listRoutes();
    }

    @RequestMapping("/menu/listMenuTree.action")
    @ResponseBody
    public List<Map<String,Object>> listMenuTree(int id){
        return menuService.listMenuTree(id);
    }

    @RequestMapping("/menu/getMenu.action")
    @ResponseBody
    public Menu getMenu(int id){
        return menuService.getMenu(id);
    }

    @RequestMapping(value = "/menu/deleteMenu.action",method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteMenu(@RequestBody Map<String,Object> data){
        System.out.println(data);
//        return false;
        return menuService.deleteMenu((int)data.get("id"));
    }

}
