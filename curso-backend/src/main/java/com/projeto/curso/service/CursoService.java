package com.projeto.curso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.curso.entity.Curso;
import com.projeto.curso.repository.ICursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

	@Autowired
	ICursoRepository repository;

	@PersistenceContext
	EntityManager em;

	public Curso save(Curso curso) {
		validaDataCadastro(curso);
		validaPeridoCadastro(curso);
		regraValidaDescricao(curso);
		return repository.save(curso);
	}

	public List<Curso> findAll(String descricao, LocalDate dataInicio, LocalDate dataTermino) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Curso> cq = cb.createQuery(Curso.class);

		Root<Curso> curso = cq.from(Curso.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (descricao != "") {
			Predicate descricaoPredicate = cb.equal(curso.get("descricao"), descricao);
			predicateList.add(descricaoPredicate);
		}

		if (dataInicio != null) {
			Predicate dataInicioPredicate = cb.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predicateList.add(dataInicioPredicate);
		}

		if (dataTermino != null) {
			Predicate dataTerminoPredicate = cb.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
			predicateList.add(dataTerminoPredicate);
		}

		Predicate[] predicateArray = new Predicate[predicateList.size()];

		predicateList.toArray(predicateArray);

		cq.where(predicateArray);

		TypedQuery<Curso> query = em.createQuery(cq);
		return query.getResultList();

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
			throw new RuntimeException("Erro no cadastro : A data inicial deve ser superior ?? " +LocalDate.now());
		}
	}

	public void validaExclusao(Curso curso) {
		if (curso.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Erro na exclus??o : Curso j?? concluiu");
		}
	}

	public void validaPeridoCadastro(Curso curso) {

		Integer listaCurso = repository.queryPeriodo(curso.getDataInicio(), curso.getDataTermino());
		if (listaCurso > 0) {
			throw new RuntimeException("Erro no cadastro : Existe(m) curso(s) planejados(s) dentro do per??odo informado");
		}

	}

	public void cursoExiste(Curso curso) {
	Integer validacaoId = repository.editar(curso.getDescricao(), curso.getIdCurso());
	if(validacaoId >0) {
		throw new RuntimeException("Erro no cadastro : Curso j?? ?? existente");
	}
	
	
}
	public void editar(Curso curso) {
		Integer editar =repository.pesquisaCurso(curso.getDataInicio(), curso.getDataTermino(), curso.getIdCurso());
		cursoExiste(curso);
		validaDataCadastro(curso);
	
		if(editar >0){
			throw new RuntimeException(" Erro na edi????o: H?? curso existente nesse periodo");
		}
		repository.save(curso);	
	
	}
	public void regraValidaDescricao(Curso curso) {

		for (Curso c : repository.findAll()) {
			if (c.getDescricao().equals(curso.getDescricao())) {
				throw new RuntimeException("Erro no cadastro : Curso com essa descri????o j?? ?? existente");
			}
		}

	}

}


	