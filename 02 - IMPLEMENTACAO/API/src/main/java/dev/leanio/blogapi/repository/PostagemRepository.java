package dev.leanio.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.repository.postagem.PostagemRepositoryQuery;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>, PostagemRepositoryQuery {
	
	public Postagem findByTitulo(String titulo);
	
}
