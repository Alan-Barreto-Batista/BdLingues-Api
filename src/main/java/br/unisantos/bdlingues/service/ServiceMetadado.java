package br.unisantos.bdlingues.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.bdlingues.model.Metadado;
import br.unisantos.bdlingues.repository.MetadadoRepository;

@Service
public class ServiceMetadado {
	
	@Autowired
	private MetadadoRepository repositoryMetadado;
		
		public ServiceMetadado() {}
		
		public Metadado create(Metadado obj) {
			return repositoryMetadado.save(obj);
		}
		
		public Metadado findById(Long id) {
		Optional<Metadado> _metadado = repositoryMetadado.findById(id);
		return _metadado.orElse(null);
		}
		
		public List<Metadado> findByFenomeno(Long idfenomeno) {
			return repositoryMetadado.listarPorFenomeno(idfenomeno);
		}
		
		public List<Metadado> findByIdInOrder(Long fenomenoId) {
			return repositoryMetadado.listarPorId();
		}
		
		public List<Metadado> findAll(){
			return repositoryMetadado.findAll();
		}
		
		public boolean update(Metadado obj) {
			if(repositoryMetadado.existsById(obj.getId())) {
				repositoryMetadado.save(obj);
			}
			return false;
		}
		
		public boolean delete(Long id) {
		Optional<Metadado> _metadado = repositoryMetadado.findById(id);	
			if(_metadado.isPresent()) {
				repositoryMetadado.delete(_metadado.get());
				return true;
			}
				return false;
			}
		

}