package br.unisantos.bdlingues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.bdlingues.model.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	
	
	public List<Categoria> listarTodos();
}