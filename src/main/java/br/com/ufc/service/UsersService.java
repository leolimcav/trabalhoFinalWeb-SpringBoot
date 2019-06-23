package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Orders;
import br.com.ufc.model.Users;
import br.com.ufc.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepo;
	
	public void create(Users user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		usersRepo.save(user);
	}
	
	public List<Users> fetchAllUsers(){
		return usersRepo.findAll();
	}
	
	public Users read(Long userId) {
		return usersRepo.getOne(userId);
	}
	
	public void delete(Long userId) {
		usersRepo.deleteById(userId);
	}

	public List<Orders> fetchAllUserOrders(Long userId) {
		Users user = usersRepo.getOne(userId);
		return user.getOrders();
	}
}
