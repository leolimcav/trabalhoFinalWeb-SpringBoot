package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Orders;
import br.com.ufc.model.Users;
import br.com.ufc.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersService userService;
	
	@RequestMapping("/list")
	public ModelAndView listAllUsers(){
		List<Users> userList = userService.fetchAllUsers();
		ModelAndView mv = new ModelAndView("ListUsers");
		System.out.println(userList);
		mv.addObject("Users", userList);
		return mv;
	}
	
	@RequestMapping("/update/{userId}")
	public ModelAndView updateUserForm(@PathVariable Long userId) {
		Users user = userService.read(userId);
		ModelAndView mv = new ModelAndView("EditUserForm");
		mv.addObject("user", user);
		return mv;
	}
	
	@DeleteMapping("/delete/{userId}")
	public ModelAndView deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
		ModelAndView mv = new ModelAndView("redirect:/users/list");
		return mv;
	}
	
	@RequestMapping("/orders/{userId}")
	public ModelAndView listUserOrders(@PathVariable Long userId) {
		List<Orders> userOrders = userService.fetchAllUserOrders(userId);
		ModelAndView mv = new ModelAndView("ListUserOrders");
//		mv.addObject("userOrders", userOrders);
		return mv;
	}
	
}
