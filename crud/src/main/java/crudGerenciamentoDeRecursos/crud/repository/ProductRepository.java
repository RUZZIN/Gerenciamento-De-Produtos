package crudGerenciamentoDeRecursos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import crudGerenciamentoDeRecursos.crud.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
