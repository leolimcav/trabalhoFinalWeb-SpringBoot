package br.com.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ufc.service.PlatesService;

@Controller
@RequestMapping("/plates")
public class PlatesController {
	
	@Autowired
	PlatesService plateService;
	
}