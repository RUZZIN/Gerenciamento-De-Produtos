package crudGerenciamentoDeRecursos.crud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crudGerenciamentoDeRecursos.crud.exception.UnsupportedProductOperationsException;
import crudGerenciamentoDeRecursos.crud.model.Product;
import crudGerenciamentoDeRecursos.crud.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private final ProductService productService;

    public ProdutoController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping    
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> products = productService.findAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao buscar produtos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@RequestParam Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Produto não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao buscar produto com o ID: " + id + ": " + e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        try {
            Product createdProduct = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Error creating product: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestParam Long id, @Valid @RequestBody Product product) {
        try {
            Product updatedProduct = productService.update(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Produto não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao atualizar produto com o ID: " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Produto não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao deletar produto com o ID: " + id + ": " + e.getMessage());
        }
    }
    
    @ExceptionHandler(UnsupportedProductOperationsException.class)
    public ResponseEntity<String> handleResourceNotFoundException(UnsupportedProductOperationsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UnsupportedProductOperationsException.class)
    public ResponseEntity<String> handleUnsupportedOperationException(UnsupportedProductOperationsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: " + e.getMessage());
    }
}
