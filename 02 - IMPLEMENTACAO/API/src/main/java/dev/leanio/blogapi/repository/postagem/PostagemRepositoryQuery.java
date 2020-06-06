package dev.leanio.blogapi.repository.postagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.leanio.blogapi.dto.postagem.PostagemOutput;
import dev.leanio.blogapi.repository.filter.PostagemFilter;

public interface PostagemRepositoryQuery {
	
	public Page<PostagemOutput> filtrar(PostagemFilter postagemFilter, Pageable pageable);

}
