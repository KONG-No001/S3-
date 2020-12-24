package com.gp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gp.vo.Menu;

import java.util.Map;

public interface MenuDao extends BaseMapper<Menu> {

    Map<String, Object> getAppendInfo(int id);

}
