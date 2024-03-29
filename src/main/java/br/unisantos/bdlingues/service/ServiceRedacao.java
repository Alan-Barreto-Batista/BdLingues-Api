package br.unisantos.bdlingues.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import br.unisantos.bdlingues.exception.StorageException;
import br.unisantos.bdlingues.exception.StorageFileNotFoundException;
import br.unisantos.bdlingues.model.Redacao;
import br.unisantos.bdlingues.repository.RedacaoRepository;
import br.unisantos.bdlingues.storage.StorageService;

@Service
public class ServiceRedacao implements StorageService {

	private final Path rootLocation;
	private String location = "uploadDir";

	public String getlocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Autowired
	private RedacaoRepository repositoryRedacao;
	
	public ServiceRedacao() {
		this.rootLocation = Paths.get(this.getlocation());
	}

	public Redacao create(Redacao obj) {
		return repositoryRedacao.save(obj);
	}

	public Redacao findById(Long id) {
		Optional<Redacao> _redacao = repositoryRedacao.findById(id);
		return _redacao.orElse(null);
	}

	public List<Redacao> findAll() {
		return repositoryRedacao.findAll();
	}

	public boolean update(Redacao obj) {
		if (repositoryRedacao.existsById(obj.getId())) {
			repositoryRedacao.save(obj);
		}
		return false;
	}

	public boolean delete(Long id) {
		Optional<Redacao> _redacao = repositoryRedacao.findById(id);
		if (_redacao.isPresent()) {
			repositoryRedacao.delete(_redacao.get());
			return true;
		}
		return false;
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Não foi possível criar o diretório", e);
		}
		
	}

	public void storeArquivo(MultipartFile arquivo) throws IOException, StorageException {
		try {
			if (arquivo.isEmpty()) {
				throw new StorageException("Falha ao guardar o arquivo vazio.");
			}
			Path destinationArquivo = this.rootLocation.resolve(Paths.get(arquivo.getOriginalFilename())).normalize()
					.toAbsolutePath();
			if (!destinationArquivo.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Não é possivel guardar o arquivo fora do diretorio atual.");
			}
			
			try (InputStream inputStream = arquivo.getInputStream()) {
				Files.copy(inputStream, destinationArquivo);
				String nomeArq = arquivo.getOriginalFilename();
				String[] dados = nomeArq.split("_");
				int last = dados.length - 1;
				dados[last] = dados[last].substring(0, dados[last].indexOf("."));
				Redacao redacao = new Redacao();
				redacao.setEscola(dados[0]);
				redacao.setAlunoId(Integer.parseInt(dados[1]));
				redacao.setSerie(Integer.parseInt(dados[2]));
				redacao.setAno(Integer.parseInt(dados[3]));
				redacao.setMes(Integer.parseInt(dados[4]));
				redacao.setArquivo(nomeArq);
				repositoryRedacao.save(redacao);
			}
		} catch (IOException e) {
			throw new StorageException("Falha ao guardar o arquivo.", e);
		}
	}
	
	@Override
	public Path load(String nomeArq) {
		return rootLocation.resolve(nomeArq);
	}
	
	@Override
	public Resource loadAsResource(String nomeArq) {
		try {
			Path arquivo = load(nomeArq);
			Resource resource = new UrlResource(arquivo.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Não foi possível ler o arquivo: " + nomeArq);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Não foi possível ler o arquivo: " + nomeArq, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
		
	}

	

}
