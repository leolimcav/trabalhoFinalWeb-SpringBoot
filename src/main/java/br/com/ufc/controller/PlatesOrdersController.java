package br.com.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ufc.service.PlatesOrdersService;

@Controller
@RequestMapping("/cart")
public class PlatesOrdersController {

	@Autowired
	PlatesOrdersService plateOrderService;
	
}
