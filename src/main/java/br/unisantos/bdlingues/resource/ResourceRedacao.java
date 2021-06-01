package br.unisantos.bdlingues.resource;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.unisantos.bdlingues.exception.StorageException;
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
	
	@GetMapping(value ="baixarRedacao/{nomeArq:.+}")
	@ResponseBody
	public ResponseEntity<Resource> baixarRedacao(@PathVariable String nomeArq) {
		Resource arquivo = service.loadAsResource(nomeArq);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + arquivo.getFilename() + "\"").body(arquivo);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Redacao obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<Void> upload(@RequestParam("arquivo") MultipartFile arquivo) {
		try { 
		service.storeArquivo(arquivo);
		return ResponseEntity.ok().build();
	} catch (IOException|StorageException  e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
}