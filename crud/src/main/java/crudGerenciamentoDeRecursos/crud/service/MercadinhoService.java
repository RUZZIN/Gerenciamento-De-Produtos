package crudGerenciamentoDeRecursos.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import crudGerenciamentoDeRecursos.crud.model.Mercadinho;
import crudGerenciamentoDeRecursos.crud.repository.MercadinhoRepository;
import lombok.AllArgsConstructor;

@Service
public class MercadinhoService {
	
	private final MercadinhoRepository mercadinhoRepository;
	
	public MercadinhoService(MercadinhoRepository mercadinhoRepository) {
        this.mercadinhoRepository = mercadinhoRepository;
    }

	public List<Mercadinho> findAll() {
		return mercadinhoRepository.findAll();
	}
	
	public Mercadinho findById(Long id) {
	    return mercadinhoRepository.findById(id)
	    .orElseThrow(() -> new RuntimeException("Mercadinho não encontrado com ID: " + id));
	}

	
	public Mercadinho save(Mercadinho mercadinho) {
	    return mercadinhoRepository.save(mercadinho);
	}

	
	public Mercadinho update(Long id, Mercadinho mercadinho) {
		Mercadinho existingMercadinho = findById(id);
		
        existingMercadinho.setNome(mercadinho.getNome());
        existingMercadinho.setEndereco(mercadinho.getEndereco());
        existingMercadinho.setTelefone(mercadinho.getTelefone());
        
        return mercadinhoRepository.save(existingMercadinho);
    }

	public void delete(Long id) {
		mercadinhoRepository.deleteById(id);
	}
	
}
