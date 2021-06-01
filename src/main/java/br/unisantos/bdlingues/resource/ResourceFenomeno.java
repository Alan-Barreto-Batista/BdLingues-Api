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

import br.unisantos.bdlingues.model.Fenomeno;
import br.unisantos.bdlingues.service.ServiceFenomeno;

@RestController
@RequestMapping("/fenomenos")
public class ResourceFenomeno {
	
	@Autowired
	public ServiceFenomeno service;

	@GetMapping
	public ResponseEntity<List<Fenomeno>> get() {
		return ResponseEntity.ok(service.findAll());
		}
	
	@GetMapping(value = "/porOrdemDeNome")
	public ResponseEntity<List<Fenomeno>> getByOrder(){
		return ResponseEntity.ok(service.findAllOrder());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Fenomeno _fenomeno = service.findById(id);
		if(_fenomeno != null) {
			return ResponseEntity.ok(_fenomeno);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	@GetMapping(value = "/categoria/{categoria}")
	public ResponseEntity<List<Fenomeno>> getByCategoria(@PathVariable("categoria") Long categoria) {
		List<Fenomeno> _fenomeno = service.findByCategoria(categoria);
		if(_fenomeno != null) {
			return ResponseEntity.ok(_fenomeno);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Fenomeno> add(@RequestBody Fenomeno obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Fenomeno obj) {
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
