package br.unisantos.bdlingues.storage;

import java.nio.file.Path;

import org.springframework.core.io.Resource;

public interface StorageService {

	void init();
	void deleteAll();
	Resource loadAsResource(String nomeArq);
	Path load(String nomeArq);
	
}
