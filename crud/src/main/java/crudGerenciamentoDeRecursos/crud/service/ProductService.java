package crudGerenciamentoDeRecursos.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import crudGerenciamentoDeRecursos.crud.model.Product;
import crudGerenciamentoDeRecursos.crud.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existingProduct = findById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        return productRepository.save(existingProduct);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
