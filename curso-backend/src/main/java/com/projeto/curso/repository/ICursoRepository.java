package com.projeto.curso.repository;

import java.time.LocalDate;
import java.util.List;

import com.projeto.curso.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository  extends JpaRepository<Curso, Long>{
	
	List<Curso> findByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(LocalDate dataInicio, LocalDate dataTermino);

}
