package com.gp.service;

import com.gp.vo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> getMenu(int id);

    List<Map<String,Object>> listRoutes();

}
