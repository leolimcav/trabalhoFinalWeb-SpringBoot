package br.com.ufc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	RolesRepository roleRepo;
	
}
