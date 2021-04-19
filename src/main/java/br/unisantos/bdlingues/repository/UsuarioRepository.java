package br.unisantos.bdlingues.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.bdlingues.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
