package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

}
