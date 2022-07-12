package com.projeto.curso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.projeto.curso.entity.Curso;
import com.projeto.curso.repository.ICursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

	@Autowired
	ICursoRepository repository;

	public Curso save(Curso curso) {
		validaDataCadastro(curso);
		validaPeridoCadastro(curso);
		return repository.save(curso);
	}

	public List<Curso> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Optional<Curso> findById(Long id) {

		return repository.findById(id);
	}

	public void delete(Curso curso) {
		validaExclusao(curso);
		repository.delete(curso);

	}

	public void validaDataCadastro(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data inicial deve ser superior à " + LocalDate.now());
		}
	}

	public void validaExclusao(Curso curso) {
		if (curso.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Não pode ser excluido, pois o curso já terminou");
		}
	}

	public void validaPeridoCadastro(Curso curso) {

		List<Curso> listaCurso = repository.findByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(curso.getDataInicio(), curso.getDataTermino());

		if (listaCurso.size()> 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado");
		}
	
	}

}
