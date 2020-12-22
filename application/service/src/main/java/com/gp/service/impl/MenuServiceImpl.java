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
import java.util.function.Consumer;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;


    @Override
    public List<Menu> listMenu(int id) {
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

    @Override
    public List<Map<String, Object>> listMenuTree(int id) {
        Menu menu = new Menu();
        menu.setPrint(id);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(menu);
        queryWrapper.select(
                "menu_id id",
                "menu_title label",
                "menu_icon icon"
        );
        return menuDao.selectMaps(queryWrapper);
    }

    @Override
    public Menu getMenu(int id) {
        Menu menu = new Menu();
        menu.setId(id);
        return menuDao.selectList(new QueryWrapper<Menu>(menu)).get(0);
    }

    @Override
    public boolean deleteMenu(int id) {
//        Menu menu = new Menu();
//        menu.setId(id);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_prints",id).or().eq("menu_id",id);
        return menuDao.delete(queryWrapper)>0;
    }
}
