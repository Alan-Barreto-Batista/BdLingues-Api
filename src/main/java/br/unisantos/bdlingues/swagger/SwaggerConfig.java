package br.unisantos.bdlingues.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI 	BdlinguesOpenAPI() {
		return new OpenAPI().info(new Info()
		.title("API do Projeto de Iniciação Científica da Unisantos")
		.description("Esta API é utilizada nas aulas de Iniciação Científica para Ensino Médio ")
		.version("v0.0.1")
		.contact(new Contact()
		.name("Alan Barreto Batista").email("alan.chessus.02@gmail.com"))
		.license(new License()
		.name("Apache 2.0").url("http://springdoc.org")));
		}
		
}
