package com.projeto.curso.repository;

import com.projeto.curso.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository  extends JpaRepository<Curso, Long>{

}
