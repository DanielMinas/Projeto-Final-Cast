package com.projeto.curso.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.projeto.curso.dto.CursoDto;
import com.projeto.curso.entity.Curso;
import com.projeto.curso.service.CursoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoService service;
	
	@PostMapping("/post")
	public ResponseEntity<Object> post(@RequestBody CursoDto dto ){
		try {
			Curso curso = new Curso();
			
			BeanUtils.copyProperties(dto, curso);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	
		}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Curso>> get(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTermino
			){
		
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(descricao, dataInicio, dataTermino));
	}
	
	  @GetMapping("/get/{id}")
	    public ResponseEntity<Object> getId(@PathVariable(value = "id") Long id){
	        Optional<Curso> cursoOptional = service.findById(id);
	        if (!cursoOptional.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há curso com este ID!!");
	        }
	        return ResponseEntity.status(HttpStatus.OK).body(cursoOptional.get());
	    }
	
	
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
		  try {
			  Optional<Curso> cursoOptional = service.findById(id);
		        if (!cursoOptional.isPresent()) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há curso com este ID!!");
		        }
		        service.delete(cursoOptional.get());
		        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		   
	    }
	  
	  @PutMapping("/put")
	    public ResponseEntity<Object> put( @RequestBody  CursoDto Dto){
		  try {
			  Optional<Curso> cursoOptional = service.findById(Dto.getIdCurso());
		        if (!cursoOptional.isPresent()) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há curso com este ID!!");
		        }
		        Curso curso = new Curso();
		        BeanUtils.copyProperties(Dto, curso);
		        service.editar(curso);
		        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com Sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		  
	    }
}
