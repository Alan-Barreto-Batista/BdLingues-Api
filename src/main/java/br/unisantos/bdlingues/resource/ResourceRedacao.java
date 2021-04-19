package br.unisantos.bdlingues.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.unisantos.bdlingues.model.Redacao;
import br.unisantos.bdlingues.service.ServiceRedacao;

@RestController
@RequestMapping("/redacoes")
public class ResourceRedacao {

	@Autowired
	public ServiceRedacao service;

	@GetMapping
	public ResponseEntity<List<Redacao>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Redacao _redacao = service.findById(id);
		if (_redacao != null) {
			return ResponseEntity.ok(_redacao);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<Redacao> add(@RequestBody Redacao obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Redacao obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<Void> upload(@RequestParam("arquivo") MultipartFile arquivo) {
		service.storeArquivo(arquivo);
		return ResponseEntity.ok().build();
	}
}
