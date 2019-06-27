package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createPlateForm() {
		ModelAndView mv = new ModelAndView("PlateForm");
		return mv;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPlate(Plates plate, @RequestParam(value="plateImage") MultipartFile image) {
		plateService.create(plate, image);
		ModelAndView mv = new ModelAndView("redirect:/plates/gallery");
		return mv;
	}
	
	@RequestMapping("/update/{plateId}")
	public ModelAndView updatePlate(@PathVariable Long plateId) {
		Plates plates = plateService.read(plateId);
		ModelAndView mv = new ModelAndView("PlateUpdate");
		mv.addObject("plate", plates);
		return mv;
	}
	
	@RequestMapping("/delete/{plateId}")
	public ModelAndView deletePlate(@PathVariable Long plateId) {
		plateService.delete(plateId);
		ModelAndView mv = new ModelAndView("redirect:/plates/gallery");
		return mv;
	}
	
	@RequestMapping("/error")
	public ModelAndView errorHandler() {
		ModelAndView mv = new ModelAndView("Error");
		return mv;
	}
}
