package br.unisantos.bdlingues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.bdlingues.model.Metadado;

@Repository
public interface MetadadoRepository extends JpaRepository<Metadado,Long> {

	public List<Metadado> listarPorId();
	
	public List<Metadado> listarPorFenomeno(Long idFenomeno);
}

