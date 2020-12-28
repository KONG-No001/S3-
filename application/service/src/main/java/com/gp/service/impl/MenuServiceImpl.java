package com.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gp.dao.MenuDao;
import com.gp.service.MenuService;
import com.gp.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;


    @Override
    public List<Menu> listMenu(int id) {
        Menu menu = new Menu();
        menu.setPrint(id);
        return menuDao.selectList(new QueryWrapper<Menu>(menu).orderByAsc("menu_index"));
    }

    @Override
    public List<Integer> listAllId() {
        List<Integer> ids = new ArrayList<>();
        for(Map<String,Object> rs : menuDao.selectMaps(new QueryWrapper<Menu>().select("menu_id id"))){
            ids.add((Integer) rs.get("id"));
        }
        return ids;
    }

    @Override
    public List<Map<String, Object>> listRoutes() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("menu_is_parent",1);
        queryWrapper.select("menu_field field","menu_url url","menu_path path").orderByAsc("menu_index");
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
        ).orderByAsc("menu_index");

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

    @Override
    public Map<String, Object> getAppendInfo(int id) {
        return menuDao.getAppendInfo(id);
    }

    @Override
    public Map<String,Object> appendMenu(Menu data) {
        Map<String,Object> map = new HashMap<>();
        menuDao.updateById(new Menu(
                data.getPrint(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                1,
                null
        ));
        map.put("state",menuDao.insert(data)>0);
        map.put("id",data.getId());
        map.put("label",data.getTitle());
        return map;
    }

    @Override
    public Map<String, Object> updateMenu(Menu data) {
        Map<String,Object> map = new HashMap<>();
        map.put("state",menuDao.updateById(data)>0);
        map.put("id",data.getId());
        map.put("label",data.getTitle());
        return map;
    }

    @Override
    public Map<String, Object> addRootMenu(Menu data) {
        data.setId(null);
        data.setPrint(0);
        data.setPrints("0");
        data.setLevel(0);
        data.setIsParent(0);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(`menu_index`) `maxMenuIndex`").eq("menu_print",0);
        data.setI((Integer) menuDao.selectMaps(queryWrapper).get(0).get("maxMenuIndex")+1);
        Map<String,Object> map = new HashMap<>();
        map.put("state",menuDao.insert(data)>0);
        map.put("id",data.getId());
        map.put("label",data.getTitle());
        return map;
    }

    @Override
    public Menu getUpdateInfo(int id) {
        return menuDao.selectById(id);
    }

    @Override
    public Map<String, Object> updateMenuNode(List<Menu> menuList) {
        Map<String,Object> map = new HashMap<>();
        int success = 0;
        int error = 0;
        int execute = 0;
        int total = menuList.size();
        for (Menu menu: menuList){
            execute++;
            try{
                if(menuDao.updateById(menu)>0){
                    success++;
                }else{
                    error++;
                }
            }catch (Throwable e){
                error++;
            }
        }

        map.put("success",success);
        map.put("error",error);
        map.put("execute",execute);
        map.put("total",total);
        return map;
    }
}
