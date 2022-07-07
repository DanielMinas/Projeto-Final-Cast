package com.projeto.curso.service;

import com.projeto.curso.entity.Curso;
import com.projeto.curso.repository.ICursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

	@Autowired
	ICursoRepository repository;
	
	
	public Curso save(Curso curso) {
		// TODO Auto-generated method stub
		return repository.save(curso);
	}

}
