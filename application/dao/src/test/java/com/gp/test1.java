package com.gp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gp.dao.GoodsDao;
import com.gp.dao.GoodsTypeDao;
import com.gp.vo.GoodsTypeVo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {

    @Test
    public void test1() {

        ApplicationContext context = new ClassPathXmlApplicationContext("dao-config.xml");

        GoodsTypeDao bean = context.getBean(GoodsTypeDao.class);
        System.out.println(bean.selectPage(new Page(1,2), null).getRecords().size());
    }
}
