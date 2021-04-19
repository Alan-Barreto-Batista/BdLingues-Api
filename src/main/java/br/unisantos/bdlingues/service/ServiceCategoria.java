package br.unisantos.bdlingues.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.bdlingues.model.Categoria;
import br.unisantos.bdlingues.repository.CategoriaRepository;


@Service
public class ServiceCategoria {
	
@Autowired
private CategoriaRepository repositoryCategoria;
	
	public ServiceCategoria() {}
	
	public Categoria create(Categoria obj) {
		return repositoryCategoria.save(obj);
	}
	
	public Categoria findById(Long id) {
	Optional<Categoria> _categoria = repositoryCategoria.findById(id);
	return _categoria.orElse(null);
	}
	
	public List<Categoria> findAllOrder()  {
		return repositoryCategoria.listarTodos();
	}
	
	public List<Categoria> findAll(){
		return repositoryCategoria.findAll();
	}
	
	public boolean update(Categoria obj) {
		if(repositoryCategoria.existsById(obj.getId())) {
			repositoryCategoria.save(obj);
		}
		return false;
	}
	
	public boolean delete(Long id) {
	Optional<Categoria> _categoria = repositoryCategoria.findById(id);	
		if(_categoria.isPresent()) {
			repositoryCategoria.delete(_categoria.get());
			return true;
		}
			return false;
		}
	
	

}

