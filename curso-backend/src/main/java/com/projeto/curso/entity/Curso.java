package com.projeto.curso.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name ="curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
	private String descricao;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "fkcategoria")
	private Categoria categoria;
	
	
	





	public Curso(Long idCurso, String descricao, LocalDate dataInicio, LocalDate dataTermino, Integer quantidade,
			Categoria categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}




	public Long getIdCurso() {
		return idCurso;
	}




	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public LocalDate getDataInicio() {
		return dataInicio;
	}




	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}




	public LocalDate getDataTermino() {
		return dataTermino;
	}




	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}




	public Integer getQuantidade() {
		return quantidade;
	}




	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}







	public Categoria getCategoria() {
		return categoria;
	}




	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}




	public Curso() {
		super();
	}
	
	
	
}
