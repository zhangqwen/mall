package com.zhangqw7.project.test;

import com.zhangqw7.project.dao.IProductDao;
import com.zhangqw7.project.model.Product;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class TestMyBatis {

    @Test
    public void run1() throws IOException {
        Product product = new Product();
        product.setName("丑柑");
        product.setPrice(500.0);
        //product.setRemark("又黄又大又好吃啊！");
        //加载配置文件

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory ssf = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
        SqlSession session = ssf.openSession();

        IProductDao dao = session.getMapper(IProductDao.class);
        //保存
        dao.saveProduct(product);
    }

    @Test
    public void run2() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory ssf = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
        SqlSession session = ssf.openSession();

        IProductDao dao = session.getMapper(IProductDao.class);

        List<Product> list = dao.findAll();
        for (Product product: list ) {
            System.out.println(product);
        }
    }
}
