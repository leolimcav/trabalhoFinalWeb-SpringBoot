package br.com.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ufc.service.RolesService;

@Controller
public class RolesController {
	
	@Autowired
	RolesService roleService;
	
}
