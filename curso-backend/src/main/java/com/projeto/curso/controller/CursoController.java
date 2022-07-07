package com.projeto.curso.controller;

import java.util.List;
import java.util.Optional;

import com.projeto.curso.dto.CursoDto;
import com.projeto.curso.entity.Curso;
import com.projeto.curso.service.CursoService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoService service;
	
	@PostMapping("/post")
	public ResponseEntity<Object> post(@RequestBody CursoDto dto ){
		Curso curso = new Curso();
		
		BeanUtils.copyProperties(dto, curso);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
		
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Curso>> get(){
		
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
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
		   Optional<Curso> cursoOptional = service.findById(id);
	        if (!cursoOptional.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há curso com este ID!!");
	        }
	        service.delete(cursoOptional.get());
	        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
	    }
	  
	  @PutMapping("/put/{id}")
	    public ResponseEntity<Object> put(@PathVariable(value = "id") Long id,
	                                                    @RequestBody  CursoDto Dto){
		  Optional<Curso> cursoOptional = service.findById(id);
	        if (!cursoOptional.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há curso com este ID!!");
	        }
	        Curso curso = new Curso();
	        BeanUtils.copyProperties(Dto, curso);
	        return ResponseEntity.status(HttpStatus.OK).body(service.save(curso));
	    }
}
