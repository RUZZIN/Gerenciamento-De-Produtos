package crudGerenciamentoDeRecursos.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crudGerenciamentoDeRecursos.crud.exception.UnsupportedProductOperationsException;
import crudGerenciamentoDeRecursos.crud.model.Mercadinho;
import crudGerenciamentoDeRecursos.crud.service.MercadinhoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mercadinhos")
public class MercadinhoController {
	
	@Autowired
	private MercadinhoService mercadinhoService;

    @GetMapping
    public ResponseEntity<List<Mercadinho>> findAll() {
        try {
            List<Mercadinho> mercadinhoList = mercadinhoService.findAll();
            if (mercadinhoList.isEmpty()) {
                return ResponseEntity.ok(mercadinhoList);
            }
            return ResponseEntity.ok(mercadinhoList);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao buscar mercadinho: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercadinho> findById(@PathVariable Long id) {
        try {
            Mercadinho mercadinho = mercadinhoService.findById(id);
            return ResponseEntity.ok(mercadinho);
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Mercadinho não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao buscar mercadinho com o ID: " + id + ": " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Mercadinho> create(@Valid @RequestBody Mercadinho mercadinho) {
        try {
            Mercadinho createdMercadinho = mercadinhoService.save(mercadinho);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMercadinho);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao criar mercadinho: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mercadinho> update(@PathVariable Long id, @Valid @RequestBody Mercadinho mercadinho) {
        try {
            Mercadinho updatedMercadinho = mercadinhoService.update(id, mercadinho);
            return ResponseEntity.ok(updatedMercadinho);
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Mercadinho não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao atualizar mercadinho com o ID: " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            mercadinhoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedProductOperationsException e) {
            throw new UnsupportedProductOperationsException("Mercadinho não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new UnsupportedProductOperationsException("Erro ao deletar mercadinho com o ID: " + id + ": " + e.getMessage());
        }
    }

    /** Tratamento de Exceções Globais */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); 
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
