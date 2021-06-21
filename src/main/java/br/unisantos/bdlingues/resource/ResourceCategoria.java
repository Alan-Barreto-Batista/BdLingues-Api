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

import br.unisantos.bdlingues.model.Categoria;
import br.unisantos.bdlingues.service.ServiceCategoria;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class ResourceCategoria {
	
	@Autowired
	public ServiceCategoria service;

	@GetMapping (produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna a lista de categorias"),
			@ApiResponse(responseCode = "500",
			description = "Erro interno de servidor"),
			})
	public ResponseEntity<List<Categoria>> get() {
		return ResponseEntity.ok(service.findAll());
		}
	
	@GetMapping(value = "/todosEmOrdem",produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna a lista de categorias em ordem alfabética"),
			@ApiResponse(responseCode = "500",
			description = "Erro interno de servidor"),
	})
	public ResponseEntity<List<Categoria>> getByOrder() {
		return ResponseEntity.ok(service.findAllOrder());
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna a categoria do número de 'id' informado"),
			@ApiResponse(responseCode = "500",
			description = "Erro interno de servidor"),
			@ApiResponse(responseCode = "404",
			description = "A categoria que procura não existe")
	})
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Categoria _categoria = service.findById(id);
		if(_categoria != null) {
			return ResponseEntity.ok(_categoria);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping (produces = "application/json")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Categoria> add(@RequestBody Categoria obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping	(produces = "application/json")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Categoria obj) {
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}",produces = "application/json")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

}

