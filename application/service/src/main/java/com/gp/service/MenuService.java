package com.gp.service;

import com.gp.vo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> listMenu(int id);

    List<Map<String,Object>> listRoutes();
    List<Map<String,Object>> listMenuTree(int id);

    Menu getMenu(int id);

    boolean deleteMenu(int id);


}
