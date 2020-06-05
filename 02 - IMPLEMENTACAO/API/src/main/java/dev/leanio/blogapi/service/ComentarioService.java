package dev.leanio.blogapi.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.repository.ComentarioRepository;
import dev.leanio.blogapi.service.exception.ComentarioNaoEncontradoException;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Transactional
	public Comentario adicionar(Comentario comentario) {
		comentario.setDataPublicacao(LocalDate.now());
		comentario.setTexto(comentario.getTexto().trim());
		
		return comentarioRepository.save(comentario);
	}
	
	@Transactional
	public Comentario atualizar(Long codigo, Comentario comentario) {
		Comentario comentarioSalvo = buscarPeloCodigo(codigo);
		
		comentario.setTexto(comentario.getTexto().trim());
		BeanUtils.copyProperties(comentario, comentarioSalvo, "codigo", "dataPublicacao", "usuario", "postagem");
		
		return comentarioRepository.save(comentarioSalvo);
	}
	
	public Comentario buscarPeloCodigo(Long codigo) {
		Optional<Comentario> comentarioSalvo = comentarioRepository.findById(codigo);
		
		return comentarioSalvo.orElseThrow(() -> new ComentarioNaoEncontradoException());
	}
	
}
