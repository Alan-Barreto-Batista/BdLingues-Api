package br.unisantos.bdlingues.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.bdlingues.model.Fenomeno;
import br.unisantos.bdlingues.repository.FenomenoRepository;

@Service
public class ServiceFenomeno {
	
@Autowired
	private FenomenoRepository repositoryFenomeno;
	
	public ServiceFenomeno() {}
	
	public Fenomeno create(Fenomeno obj) {
		return repositoryFenomeno.save(obj);
	}
	
	public Fenomeno findById(Long id) {
	Optional<Fenomeno> _fenomeno = repositoryFenomeno.findById(id);
	return _fenomeno.orElse(null);
	}
	
	public List<Fenomeno> findByCategoria(Long idCategoria){
		return repositoryFenomeno.listarPorCategoria(idCategoria);
	}
	
	public 	List<Fenomeno> findAllOrder() {
		return repositoryFenomeno.listarTodos();
	}
	public List<Fenomeno> findAll(){
		return repositoryFenomeno.findAll();
	}
	
	public boolean update(Fenomeno obj) {
		if(repositoryFenomeno.existsById(obj.getId())) {
			repositoryFenomeno.save(obj);
		}
		return false;
	}
	
	public boolean delete(Long id) {
	Optional<Fenomeno> _fenomeno = repositoryFenomeno.findById(id);	
		if(_fenomeno.isPresent()) {
			repositoryFenomeno.delete(_fenomeno.get());
			return true;
		}
			return false;
		}
	
}

