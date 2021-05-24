package br.unisantos.bdlingues.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unisantos.bdlingues.config.JWTUtil;
import br.unisantos.bdlingues.config.UserDetailsImpl;
import br.unisantos.bdlingues.exception.AuthorizationException;
import br.unisantos.bdlingues.model.Usuario;
import br.unisantos.bdlingues.repository.UsuarioRepository;

@Service
public class ServiceUsuario {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepository repositoryUsuario;
	@Autowired
	private JWTUtil jwtUtil;
	public ServiceUsuario() {

	}

	public Usuario create(Usuario obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repositoryUsuario.save(obj);
		return obj;
	}

	public Usuario findById(Long id) throws
										AuthorizationException{
	if(!jwtUtil.authorized(id)){ 
		throw new AuthorizationException("Acesso Negado!");
	}
		Optional<Usuario> _usuario = repositoryUsuario.findById(id);
		return _usuario.orElse(null);
	}
	
	public List<Usuario> findAll() {
		return repositoryUsuario.findAll();
	}

	public boolean update(Usuario obj) {
		if (repositoryUsuario.existsById(obj.getId())) {
			repositoryUsuario.save(obj);
		}
		return false;
	}

	public boolean delete(Long id) {
		Optional<Usuario> _usuario = repositoryUsuario.findById(id);
		if (_usuario.isPresent()) {
			repositoryUsuario.delete(_usuario.get());
			return true;
		}
		return false;
	}
	
	public static UserDetailsImpl authenticated() { 
		Authentication auth =  SecurityContextHolder
								.getContext().getAuthentication();
		if(auth != null) {
			return(UserDetailsImpl) auth.getPrincipal();
		}
			return null;
	}
}
