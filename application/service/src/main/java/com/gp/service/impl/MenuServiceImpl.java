package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gp.dao.MenuDao;
import com.gp.service.MenuService;
import com.gp.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
