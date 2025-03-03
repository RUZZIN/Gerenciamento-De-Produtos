package crudGerenciamentoDeRecursos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crudGerenciamentoDeRecursos.crud.model.Mercadinho;

@Repository
public interface MercadinhoRepository extends JpaRepository<Mercadinho, Long> {
	
}
