package com.gp.service;

import com.gp.vo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> listMenu(int id);
    List<Integer> listAllId();

    List<Map<String,Object>> listRoutes();
    List<Map<String,Object>> listMenuTree(int id);

    Menu getMenu(int id);

    boolean deleteMenu(int id);

    Map<String,Object> getAppendInfo(int id);

    Map<String,Object> appendMenu(Menu data);
    Map<String,Object> updateMenu(Menu data);
    Map<String,Object> addRootMenu(Menu data);

    Menu getUpdateInfo(int id);

    Map<String,Object> updateMenuNode(List<Menu> menuList);


}
