package com.projeto.curso.service;

import java.util.List;

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


	public List<Curso> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	

}
