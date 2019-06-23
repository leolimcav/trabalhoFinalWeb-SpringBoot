package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Orders;
import br.com.ufc.repository.OrdersRepository;

@Service
public class OrdersService {

	@Autowired
	OrdersRepository orderRepo;
	
	public void create(Orders order) {
		orderRepo.save(order);
	}
	
	public List<Orders> fetchAllOrders(){
		return orderRepo.findAll();
	}
	
	public Orders read(Long orderId) {
		return orderRepo.getOne(orderId);
	}
	
	public void delete(Long orderId) {
		orderRepo.deleteById(orderId);
	}
}
