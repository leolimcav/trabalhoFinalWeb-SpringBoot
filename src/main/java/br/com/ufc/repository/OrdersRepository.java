package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
