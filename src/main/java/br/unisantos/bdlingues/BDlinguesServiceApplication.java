package br.unisantos.bdlingues;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.unisantos.bdlingues.storage.StorageService;

@SpringBootApplication

public class BDlinguesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BDlinguesServiceApplication.class, args);
	}
	
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
