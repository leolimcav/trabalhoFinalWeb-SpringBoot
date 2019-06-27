package br.com.ufc.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Orders;
import br.com.ufc.model.Plates;
import br.com.ufc.model.Users;
import br.com.ufc.service.PlatesService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	PlatesService plateServ;
	
	@Autowired
	OrdersController orderControl;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping("/view")
	public ModelAndView viewCart(HttpSession session) {
		ModelAndView mv = new ModelAndView("Cart");
		return mv;
	}
	
	@RequestMapping("/buy/{plateId}")
	public ModelAndView addItem(@PathVariable Long plateId, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/plates/gallery");
		if(session.getAttribute("cart") == null) {
			List<Plates> cart = new ArrayList<Plates>();
			Plates plate = plateServ.read(plateId);
			cart.add(plate);
			session.setAttribute("cart", cart);
			session.setAttribute("total", getTotal(cart));
			session.setAttribute("qty", cart.size());
		}else {
			List<Plates> cart = (List<Plates>) session.getAttribute("cart");
			Plates plate = plateServ.read(plateId);
			cart.add(plate);
			session.setAttribute("total", getTotal(cart));
			session.setAttribute("qty", cart.size());
		}
		System.out.println("SESSÃO: "+session.getAttribute("cart").toString());
			return mv;
	}
	
	@RequestMapping("/remove/{plateId}")
	public ModelAndView removeItem(@PathVariable Long plateId, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/cart/view");
		List<Plates> cart = (List<Plates>) session.getAttribute("cart");
		Plates pl = null;
		for(Plates p : cart) {
			if(p.getPlateId() == plateId) {
				pl = p;
			}
		}
		cart.remove(pl);
		session.setAttribute("total", getTotal(cart));
		session.setAttribute("qty", cart.size());
		return mv;
	}
	
	@RequestMapping("/checkout")
	public ModelAndView checkout(HttpSession session) {
		List<Plates> cart = (List<Plates>) session.getAttribute("cart");
		Users user = (Users) session.getAttribute("user");
		Orders order = new Orders();
		order.setPlates(cart);
		order.setTotal(getTotal(cart));
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		order.setUser(user);
		String msg = "";
		for(Plates p : cart) {
			msg+= "\n"+p.getPlateName() + "\n";
		}
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("LA SAVEUR Shopping Confirmation");
		mail.setText(
	            "Dear " + user.getFullName()
	                + ", thank you for placing order. \nYour ordered items are \n"
	                + msg + "\n\nTotal: $"+getTotal(cart));
		mailer.send(mail);
		
		cart.removeAll(cart);
		session.setAttribute("total", getTotal(cart));
		session.setAttribute("qty", cart.size());
		ModelAndView mv = orderControl.createOrder(order);
		return mv;
	}
	
	private double getTotal(List<Plates> cart) {
		double total = 0;
		for(Plates p : cart) {
			total += p.getPrice();
		}
		return total;
	}
	
}
