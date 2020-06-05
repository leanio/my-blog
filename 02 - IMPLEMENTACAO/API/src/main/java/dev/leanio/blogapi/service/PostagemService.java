package dev.leanio.blogapi.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.repository.PostagemRepository;
import dev.leanio.blogapi.service.exception.PostagemComTituloJaCadastradoException;
import dev.leanio.blogapi.service.exception.PostagemNaoEncontradaException;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Transactional
	public Postagem adicionar(Postagem postagem) {
		configurarPostagem(postagem);
		postagem.setDataPublicacao(LocalDate.now());
		
		validarNovaPostagem(postagem);
		
		return postagemRepository.save(postagem);
	}
	
	@Transactional
	public Postagem atualizar(Long codigo, Postagem postagem) {
		configurarPostagem(postagem);
		
		validarAtualizacaoPostagem(codigo, postagem);		

		Postagem postagemSalva = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(postagem, postagemSalva, "codigo", "dataPublicacao", "usuario");
		
		return postagemRepository.save(postagemSalva);
	}
	
	public Postagem buscarPeloCodigo(Long codigo) {
		Optional<Postagem> postagemSalva = postagemRepository.findById(codigo);
		
		return postagemSalva.orElseThrow(() -> new PostagemNaoEncontradaException());
	}
	
	private void configurarPostagem(Postagem postagem) {
		postagem.setTitulo(postagem.getTitulo().trim());
		postagem.setCorpo(postagem.getCorpo().trim());
	}

	private void validarNovaPostagem(Postagem postagem) {
		Postagem postagemTitulo = postagemRepository.findByTitulo(postagem.getTitulo());
		
		if (postagemTitulo != null) {
			throw new PostagemComTituloJaCadastradoException();
		}
	}
	
	private void validarAtualizacaoPostagem(Long codigo, Postagem postagem) {
		Postagem postagemTitulo = postagemRepository.findByTitulo(postagem.getTitulo());
		
		if (postagemTitulo != null) {
			if (!postagemTitulo.equals(codigo)) {
				throw new PostagemComTituloJaCadastradoException();
			}
		}
	}

}
