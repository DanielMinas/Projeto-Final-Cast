package com.projeto.curso.dto;

import java.time.LocalDate;

import com.projeto.curso.entity.Categoria;

public class CursoDto {

	private Long idCurso;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private Integer quantidade;
	private Categoria categoria;
	

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



}
