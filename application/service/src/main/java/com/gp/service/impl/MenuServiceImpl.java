package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gp.dao.MenuDao;
import com.gp.service.MenuService;
import com.gp.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;


    @Override
    public List<Menu> getMenu(int id) {
        Menu menu = new Menu();
        menu.setPrint(id);
        return menuDao.selectList(new QueryWrapper<Menu>(menu));
    }

    @Override
    public List<Map<String, Object>> listRoutes() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("menu_is_parent",1);
        queryWrapper.select("menu_field field","menu_url url","menu_path path");
        return menuDao.selectMaps(queryWrapper);
    }
}
