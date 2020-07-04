package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.dao.OrderMapper;
import com.tedu.pojo.Door;
import com.tedu.pojo.Order;

@Controller
public class OrderController {
	
	@Autowired
	OrderMapper dao;
	@Autowired
	DoorMapper doorMapper;
	
	/** 1、查询所有订单信息 */
	@RequestMapping("/orderList")
	public String orderList( Model model ) {
		//查询所有订单信息, 返回所有订单的集合
		List<Order> list = dao.findAll();
		//将所有订单集合存入Model中, 转发带到JSP显示
		model.addAttribute("oList", list);
		//查询所有门店信息, 返回所有门店的集合
		List<Door> dList = doorMapper.findAll();
		model.addAttribute("dList", dList);
		return "order_list";
	}
	
	/** 2、根据id删除订单信息 */
	@RequestMapping("/orderDelete")
	public String orderDelete(Integer id) {
		dao.deleteById( id );
		//转发到查询所有订单的方法,查询最新的订单信息
		return "forward:/orderList";
	}
	
	/**
	 * 查询所有门店信息, 存入域中
	 * 再跳转到订单新增页面
	 */
	@RequestMapping("/toOrderAdd")
	public String toOrderAdd(Model model) {
		//查询所有门店信息, 返回所有门店的集合
		List<Door> dList = doorMapper.findAll();
		//将门店集合存入Model
		model.addAttribute("dList", dList);
		return "order_add";
	}
	
	/** 3、新增订单信息 */
	@RequestMapping("/orderAdd")
	public String orderAdd( Order order ) {
		dao.addOrder( order );
		//转发到查询所有订单的方法,查询最新的订单信息
		return "forward:/orderList";
	}
	/** 4、根据id查询订单信息 */
	@RequestMapping("/orderInfo")
	public String orderInfo(Integer id, Model model) {
		//查询所有门店信息的集合
		List<Door> dList = doorMapper.findAll();
		//将门店集合存入model中
		model.addAttribute("dList", dList);
		
		Order order = dao.findById( id );
		//将订单信息存入model域中
		model.addAttribute( "order", order );
		//将查询到的订单信息带到修改页面进行回显
		return "order_update";
	}
	/** 5、根据id修改订单信息 */
	@RequestMapping("/orderUpdate")
	public String orderUpdate( Order order ) {
		//将修改后的订单信息传入到修改的方法中进行修改
		dao.updateById( order );
		//转发到查询所有订单的方法,查询最新的订单信息
		return "forward:/orderList";
	}
	
	
	
	
	
	
}








