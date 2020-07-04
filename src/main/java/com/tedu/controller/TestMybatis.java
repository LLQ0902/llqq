package com.tedu.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/**
 * 测试Mybatis快速入门程序
 */
public class TestMybatis {
	
	/* 练习1: 查询所有员工信息 List<Emp> */
	@Test
	public void testFindAll() throws Exception {
		//1.读取mybaits核心配置文件(mybatis-config.xml)
		InputStream in = Resources
				.getResourceAsStream("mybatis/mybatis-config.xml");
		//2.通过配置获取一个SqlSessionFactory对象
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build( in );
		//3.通过工厂获取一个SqlSession对象(Connection)
		SqlSession session = fac.openSession();
		//4.获取DoorMapper接口的子类实例
		DoorMapper dao = session.getMapper( DoorMapper.class );
		List<Door> doorList = dao.findAll();
		//5.输出结果
		for (Door door : doorList) {
			System.out.println( door );
		}
	}
}












