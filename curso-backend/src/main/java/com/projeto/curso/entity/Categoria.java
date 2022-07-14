package com.projeto.curso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	private String categoria;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Categoria(Long idCategoria, String categoria) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
	}
	public Categoria(Long idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}

	public Categoria() {
		super();
	}

}
