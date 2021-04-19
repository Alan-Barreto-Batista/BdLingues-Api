package br.unisantos.bdlingues.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fenomeno")
@NamedQueries({
	@NamedQuery(name = "Fenomeno.listarTodos",
			query = "select f from Fenomeno f order by f.nome"),
	@NamedQuery(name = "Fenomeno.listarPorCategoria",
			query = "select f from Fenomeno f where f.categoria.id =?1 order by f.nome")
})
public class Fenomeno implements Serializable {
	
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
	
	@Column(length = 50)
	private String nome;
	@ManyToOne
	private Categoria categoria;
	
	public Fenomeno() {
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return nome;
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
		Fenomeno other = (Fenomeno) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
