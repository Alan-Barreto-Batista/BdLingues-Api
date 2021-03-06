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

import br.unisantos.bdlingues.model.Metadado;
import br.unisantos.bdlingues.service.ServiceMetadado;

@RestController
@RequestMapping("/metadados")
public class ResourceMetadado {
	
	@Autowired
	public ServiceMetadado service;

	@GetMapping
	public ResponseEntity<List<Metadado>> get() {
		return ResponseEntity.ok(service.findAll());
		}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Metadado _metadado = service.findById(id);
		if(_metadado != null) {
			return ResponseEntity.ok(_metadado);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	@GetMapping(value = "/fenomeno/{fenomeno}")
	public ResponseEntity<List<Metadado>> getByFenomeno(@PathVariable("fenomeno") Long fenomeno) {
		List<Metadado> _metadado = service.findByFenomeno(fenomeno); 
		if(_metadado != null) {
			return ResponseEntity.ok(_metadado);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Metadado> add(@RequestBody Metadado obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Metadado obj) {
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
}
