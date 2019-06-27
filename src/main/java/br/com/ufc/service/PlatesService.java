package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ufc.model.Plates;
import br.com.ufc.repository.PlatesRepository;
import br.com.ufc.util.ImageFileUtils;

@Service
public class PlatesService {
	
	@Autowired
	PlatesRepository plateRepo;

	public void create(Plates plate, MultipartFile image) {
		String path = "images/" + plate.getPlateName()+ ".jpg";
		ImageFileUtils.saveImage(path, image);
		plateRepo.save(plate);
	}
	
	public List<Plates> fetchAllPlates(){
		return plateRepo.findAll();
	}
	
	public Plates read(Long plateId) {
		return plateRepo.getOne(plateId);
	}
	
	public void delete(Long plateId) {
		plateRepo.deleteById(plateId);
	}
	
}
