package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{

}
