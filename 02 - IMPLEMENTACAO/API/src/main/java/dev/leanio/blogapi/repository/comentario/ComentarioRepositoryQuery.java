package dev.leanio.blogapi.repository.comentario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.leanio.blogapi.dto.comentario.ComentarioOutput;
import dev.leanio.blogapi.repository.filter.ComentarioFilter;

public interface ComentarioRepositoryQuery {

	public Page<ComentarioOutput> filtrar(ComentarioFilter comentarioFilter, Pageable pageable);
	
}
