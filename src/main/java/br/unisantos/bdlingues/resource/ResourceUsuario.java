package br.unisantos.bdlingues.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisantos.bdlingues.model.Usuario;
import br.unisantos.bdlingues.service.ServiceUsuario;

@RestController
@RequestMapping("/usuarios")
public class ResourceUsuario {

	@Autowired
	public ServiceUsuario service;

	@GetMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Usuario>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Usuario _usuario = service.findById(id);
		if (_usuario != null) {
			return ResponseEntity.ok(_usuario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Usuario> add(@RequestBody Usuario obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Usuario obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
