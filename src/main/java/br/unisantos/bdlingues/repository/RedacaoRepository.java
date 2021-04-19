package br.unisantos.bdlingues.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;


import br.unisantos.bdlingues.model.Redacao;

public interface RedacaoRepository extends JpaRepository<Redacao,Long> {
	
	
}

