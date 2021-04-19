package br.unisantos.bdlingues.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.bdlingues.model.Usuario;
import br.unisantos.bdlingues.repository.UsuarioRepository;

@Service
public class ServiceUsuario {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	public ServiceUsuario() {
		
	}
	public Usuario create(Usuario obj) {
		return repositoryUsuario.save(obj);
	}
	
	public Usuario findById(Long id) {
	Optional<Usuario> _usuario = repositoryUsuario.findById(id);
	return _usuario.orElse(null);
	}
	
	public List<Usuario> findAll() {
		return repositoryUsuario.findAll();
	}
	
	public boolean update(Usuario obj) {
		if(repositoryUsuario.existsById(obj.getId())) {
		repositoryUsuario.save(obj);
		}
		return false;
	}
	public boolean delete(Long id) {
	Optional<Usuario> _usuario = repositoryUsuario.findById(id);
	if(_usuario.isPresent()) {
		repositoryUsuario.delete(_usuario.get());
		return true;
	}
		return false;
	}
}

