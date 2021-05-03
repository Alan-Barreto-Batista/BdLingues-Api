package br.unisantos.bdlingues.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	public CredenciaisDTO() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void SetSenha(String senha) {
		this.senha = senha;
	}
}
