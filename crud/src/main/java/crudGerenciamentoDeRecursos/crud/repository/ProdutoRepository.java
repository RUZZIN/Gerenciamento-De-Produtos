package crudGerenciamentoDeRecursos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crudGerenciamentoDeRecursos.crud.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}