package br.com.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Users;
import br.com.ufc.service.UsersService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	UsersService userService;
	
	@RequestMapping("/")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = new ModelAndView("Index");
		System.out.println("session: "+ session.getAttribute("user"));
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Index");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView createUserForm() {
		ModelAndView mv = new ModelAndView("UserForm");
		return mv;
	}
	
	@PostMapping("/register")
	public ModelAndView createUser(Users user) {
		userService.create(user);
		ModelAndView mv = new ModelAndView("Index");
		return mv;
	}
	
}
