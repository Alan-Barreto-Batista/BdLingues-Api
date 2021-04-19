package br.unisantos.bdlingues.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_categoria")
@NamedQuery(name = "Categoria.listarTodos",
			query = "select c from Categoria c order by c.nome")
	public class Categoria implements Serializable {
		
		private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		@Column(length = 30)
		private String nome;
		@OneToMany( mappedBy = "categoria")
		
		private List<Fenomeno> fenomenos;
		
		public Categoria() {	
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		@JsonIgnore
		public List<Fenomeno> getFenomenos() {
			return fenomenos;
		}
		@JsonProperty
		public void setFenomenos(List<Fenomeno> fenomenos) {
			this.fenomenos = fenomenos;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
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
			Categoria other = (Categoria) obj;
			if (id != other.id)
				return false;
			return true;
		}
		
	}
