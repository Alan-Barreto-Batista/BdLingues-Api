package br.unisantos.bdlingues.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "nome", length = 60)
	private String nome;
	@Column(name = "email", length = 40, unique = true)
	private String email;
	@Column(name = "telefone", length = 20)
	private String telefone;
	@Column(name = "instituicao", length = 80)
	private String instituicao;
	@Column(name = "formacao")
	private String tipoFormacao;
	@Column(name = "area_curso", length = 40)
	private String areaOuCurso;
	@Column(name = "finalidade")
	private String TipoFinalidade;
	@Column(name = "finalidade_outro")
	private String finalidadeOutro;
	@Column(name = "senha")
	private String senha;
	@Column(name = "admin")
	private Boolean admin;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getTipoFormacao() {
		return tipoFormacao;
	}

	public void setTipoFormacao(String tipoFormacao) {
		this.tipoFormacao = tipoFormacao;
	}

	public String getAreaOuCurso() {
		return areaOuCurso;
	}

	public void setAreaOuCurso(String areaOuCurso) {
		this.areaOuCurso = areaOuCurso;
	}

	public String getTipoFinalidade() {
		return TipoFinalidade;
	}

	public void setTipoFinalidade(String tipoFinalidade) {
		TipoFinalidade = tipoFinalidade;
	}

	public String getFinalidadeOutro() {
		return finalidadeOutro;
	}

	public void setFinalidadeOutro(String finalidadeOutro) {
		this.finalidadeOutro = finalidadeOutro;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
