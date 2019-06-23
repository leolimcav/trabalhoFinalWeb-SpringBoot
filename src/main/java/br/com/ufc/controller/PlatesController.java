package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Plates;
import br.com.ufc.service.PlatesService;

@Controller
@RequestMapping("/plates")
public class PlatesController {
	
	@Autowired
	PlatesService plateService;
	
	@RequestMapping("/gallery")
	public ModelAndView listAllPlates(){
		List<Plates> plateList = plateService.fetchAllPlates();
		ModelAndView mv = new ModelAndView("PlateGallery");
		mv.addObject("plates", plateList);
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView createPlateForm() {
		ModelAndView mv = new ModelAndView("PlateForm");
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView createPlate(Plates plate) {
		plateService.create(plate);
		ModelAndView mv = new ModelAndView("redirect:/plates/gallery");
		return mv;
	}
	
	@RequestMapping("/update/{plateId}")
	public ModelAndView updatePlate(@PathVariable Long plateId) {
		Plates plate = plateService.read(plateId);
		ModelAndView mv = new ModelAndView("PlateForm");
		mv.addObject("plate", plate);
		return mv;
	}
	
	@RequestMapping("/delete/{plateId}")
	public ModelAndView deletePlate(@PathVariable Long plateId) {
		plateService.delete(plateId);
		ModelAndView mv = new ModelAndView("redirect:/plates/gallery");
		return mv;
	}
}
