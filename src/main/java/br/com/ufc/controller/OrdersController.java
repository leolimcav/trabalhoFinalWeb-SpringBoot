package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Orders;
import br.com.ufc.service.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	OrdersService orderService;

	@RequestMapping("/list")
	public ModelAndView listAllOrders(){
		List<Orders> orderList = orderService.fetchAllOrders();
		ModelAndView mv = new ModelAndView("OrderList");
		mv.addObject("orders", orderList);
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView createOrderForm() {
		ModelAndView mv = new ModelAndView("OrderForm");
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView createOrder(Orders order) {
		orderService.create(order);
		ModelAndView mv = new ModelAndView("redirect:/orders/list");
		return mv;
	}
	
	@RequestMapping("/update/{orderId}")
	public ModelAndView updateOrder(@PathVariable Long orderId) {
		Orders order = orderService.read(orderId);
		ModelAndView mv = new ModelAndView("OrderForm");
		mv.addObject("order", order);
		return mv;
	}
	
	@RequestMapping("/delete/{orderId}")
	public ModelAndView deleteOrder(@PathVariable Long orderId) {
		orderService.delete(orderId);
		ModelAndView mv = new ModelAndView("redirect:/orders/list");
		return mv;
	}
}
