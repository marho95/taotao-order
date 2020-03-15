package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

/**
 * 订单Controller
 * <p>Title: OrderController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月16日上午10:46:50
 * @version 1.0
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//★该方法接收的是json格式的字符串，需要使用@RequestBody注解，将接收的json格式字符串转换成Java对象
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody//@ResponseBody可将对象转换为json字符串返回
	public TaotaoResult createOrder(@RequestBody Order order) {//这里的Order继承自TbOrder，是对应接收json格式的pojo，order中存储着订单商品对象列表和订单物流对象，order相当于存储着需要向tb_order、tb_order_item、tb_order_shipping这三个表中插入的所有对象信息
		try {
			TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
