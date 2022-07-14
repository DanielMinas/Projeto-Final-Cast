package com.projeto.curso.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CursoLog {

	@Column(name="usuario_criador")
	@CreatedBy
	private String usuarioCriacao;
	@Column(name="data_criacao")
	@CreatedDate
	private Instant dataCriacao = Instant.now();
	@LastModifiedBy
	@Column(name="usuario_antigo")
	private String antigoUsuario;
	@LastModifiedDate
	@Column(name="data_modificada")
	private Instant dataModificada = Instant.now();
	
	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	public Instant getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Instant dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getAntigoUsuario() {
		return antigoUsuario;
	}
	public void setAntigoUsuario(String antigoUsuario) {
		this.antigoUsuario = antigoUsuario;
	}
	public Instant getDataModificada() {
		return dataModificada;
	}
	public void setDataModificada(Instant dataModificada) {
		this.dataModificada = dataModificada;
	}
	public CursoLog(String usuarioCriacao, Instant dataCriacao, String antigoUsuario, Instant dataModificada) {
		super();
		this.usuarioCriacao = usuarioCriacao;
		this.dataCriacao = dataCriacao;
		this.antigoUsuario = antigoUsuario;
		this.dataModificada = dataModificada;
	}
	public CursoLog() {
		super();
	}
	
	
	
	
}
