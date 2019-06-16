package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufc.model.Plates;

@Repository
public interface PlatesRepository extends JpaRepository<Plates, Long>{

}
