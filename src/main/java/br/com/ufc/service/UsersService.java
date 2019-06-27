package br.com.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Orders;
import br.com.ufc.model.Roles;
import br.com.ufc.model.Users;
import br.com.ufc.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	OrdersService orderServ;
	
	public void create(Users user) {
		List<Roles> role = new ArrayList<Roles>();
		if(user.getRole().isEmpty()) {
			role.add(new Roles("ROLE_USER"));
			user.setRole(role);
		}
			
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
//		List <Orders> orders = new ArrayList<Orders>();
//		for(Orders o : orderServ.fetchAllOrders()){
//			if(o.getUser().equals(user)) {
//				orders.add(o);
//			}
//		}
//		user.setOrders(orders);
		return user.getOrders();
	}
}
