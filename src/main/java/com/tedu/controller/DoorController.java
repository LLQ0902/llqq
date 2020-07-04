package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/* @Controller注解的作用:
 * (1)标识当前类属于Controller层
 * (2)标记当前类的对象的创建交给spring容器负责
 */
@Controller
public class DoorController {
	/* 自动装配: 可以为当前变量自动赋值: 到spring容器中根据类型匹配
	 * 	DoorMapper接口子类实例 */
	@Autowired 
	DoorMapper dao;
	
	/* 1、查询所有门店信息 */
	@RequestMapping("/doorList")
	public String doorList( Model  model ) {
		//查询所有门店信息(DoorMapper)
		List<Door> list = dao.findAll();
		//将门店集合存入Model域中, 通过转发带到jsp进行显示
		model.addAttribute( "list", list );
		//(return默认就是转发到指定名字的JSP)
		return "door_list";
	}
	
	/* 2、根据id删除门店信息 */
	@RequestMapping("/doorDelete")
	public String doorDelete(Integer id) {
		dao.deleteById( id );
		//跳转回门店列表页面(查询最新的门店信息)
		return "forward:/doorList";
	}
	/* 3、新增门店信息 */
	@RequestMapping("/doorAdd")
	public String doorAdd(Door door) {
		dao.addDoor( door );
		//跳转回门店列表页面(查询最新的门店信息)
		return "forward:/doorList";
	}
	
	/* 4、根据id查询门店信息 */
	@RequestMapping("/doorInfo")
	public String doorInfo( Integer id, Model model ) {
		Door door = dao.findById( id );
		//将门店信息存入Model域中, 转发带到门店修改页面
		model.addAttribute( "door",  door ); 
		return "door_update";
	}
	
	/* 5、根据id修改门店信息 */
	@RequestMapping("/doorUpdate")
	public String doorUpdate( Door door ) {
		dao.updateById( door );
		//跳转回门店列表页面(查询最新的门店信息)
		return "forward:/doorList";
	}
	
	
	
	/*
	 * 通用的页面跳转方法
	 * 如果访问的路径为 "/index" 那么{}里面的page的值则为 "index"
	 * 再将{}中page的值传递给方法上的形参page, 此时形参page的值
	 * 也为 "index", 再将 page的值作为返回值返回。
	 * 因此，以后如果想访问 WEB-INF/pages目录下的jsp文件, 只需要
	 * 在访问时, 访问路径为 "/jsp的名字" 就可以访问到指定名字的jsp!!
	 */
	@RequestMapping("/{page}")
	public String toPage(@PathVariable String page) {
		return page;
	}
	
	
}
