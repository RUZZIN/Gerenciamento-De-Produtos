package crudGerenciamentoDeRecursos.crud.service;

import java.util.List;
import org.springframework.stereotype.Service;
import crudGerenciamentoDeRecursos.crud.model.Produto;
import crudGerenciamentoDeRecursos.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {    
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto produto) {
        Produto existingProduto = findById(id);

        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setQuantidade(produto.getQuantidade());

        return produtoRepository.save(existingProduto);
    }

    public void delete(Long id) {
        Produto produto = findById(id);
        produtoRepository.delete(produto);
    }
}
