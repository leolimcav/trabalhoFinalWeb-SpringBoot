package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.PlatesOrders;

@Repository
public interface PlatesOrdersRepository extends JpaRepository<PlatesOrders, Long>{

}
