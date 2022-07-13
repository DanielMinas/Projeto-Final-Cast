package com.projeto.curso.repository;

import java.time.LocalDate;
import java.util.List;

import com.projeto.curso.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

	@Query("select count(*) from Curso c where (:dataInicio <= c.dataInicio and :dataTermino >= c.dataInicio)" + "OR"
            + "                                   (:dataInicio <= c.dataTermino and :dataTermino >= c.dataTermino)" + "OR"
            + "                                   (:dataInicio >= c.dataInicio and :dataTermino <= c.dataTermino) "+"OR"
            +"                                  (:dataInicio <= c.dataInicio and :dataTermino >= c.dataTermino)"
            )
    Integer queryPeriodo(LocalDate dataInicio, LocalDate dataTermino);

}
