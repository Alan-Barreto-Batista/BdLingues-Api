package br.unisantos.bdlingues.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_metadado")
@NamedQueries({
	@NamedQuery(name = "Metadado.listarPorId", 
			    query = "select m from Metadado m where m.redacao.id = ?1"),
	@NamedQuery(name = "Metadado.listarPorFenomeno",
	            query = "select m from Metadado m join m.fenomenos f where f.id = ?1")
})
public class Metadado implements Serializable {
	
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
		
	public Metadado() {}
	
	private Integer coordX;
	private Integer coordY;
	private Integer largura;
	private Integer altura;
	private String grafado;
	private String correto;
	@ManyToOne
	private Redacao redacao;
	@ManyToMany
	private List<Fenomeno> fenomenos;

	public Integer getCoordX() {
		return coordX;
	}
	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}
	public Integer getCoordY() {
		return coordY;
	}
	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}
	public Integer getLargura() {
		return largura;
	}
	public void setLargura(Integer largura) {
		this.largura = largura;
	}
	public Integer getAltura() {
		return altura;
	}
	public void setAltura(Integer altura) {
		this.altura = altura;
	}
	public String getGrafado() {
		return grafado;
	}
	public void setGrafado(String grafado) {
		this.grafado = grafado;
	}
	public String getCorreto() {
		return correto;
	}
	public void setCorreto(String correto) {
		this.correto = correto;
	}
	public Redacao getRedacao() {
		return redacao;
	}
	public void setRedacao(Redacao redacao) {
		this.redacao = redacao;
	}
	public List<Fenomeno> getFenomenos() {
		return fenomenos;
	}
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
		Metadado other = (Metadado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
