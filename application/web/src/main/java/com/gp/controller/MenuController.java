package com.gp.controller;

import com.gp.service.MenuService;
import com.gp.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return menuService.getMenu(id);
    }


    @RequestMapping("/listRoutes.action")
    @ResponseBody
    public List<Map<String,Object>> listRoutes(){
        return menuService.listRoutes();
    }


}
