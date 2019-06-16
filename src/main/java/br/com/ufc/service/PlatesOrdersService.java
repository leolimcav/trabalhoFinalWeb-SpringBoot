package br.com.ufc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.repository.PlatesOrdersRepository;

@Service
public class PlatesOrdersService {

	@Autowired
	PlatesOrdersRepository plateOrderRepo;
	
}
