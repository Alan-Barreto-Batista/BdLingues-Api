package br.unisantos.bdlingues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.bdlingues.model.Fenomeno;

@Repository
public interface FenomenoRepository extends JpaRepository<Fenomeno,Long> {
	
	public List<Fenomeno> listarTodos();
	
	public List<Fenomeno> listarPorCategoria(Long idCategoria);
	
}

