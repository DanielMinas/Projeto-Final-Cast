package com.projeto.curso.service;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class CursoLogService  implements AuditorAware<String>{



	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Daniel");
	}
	
}
