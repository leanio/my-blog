package dev.leanio.blogapi.dto.postagem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import dev.leanio.blogapi.domain.Postagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemOutput {
	
	private Long codigo;
	
	private String titulo;
	
	private String corpo;
	
	private LocalDate dataPublicacao;
	
	private String autor;
	
	public PostagemOutput(Long codigo, String titulo, String corpo, LocalDate dataPublicacao, String autor) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.corpo = corpo;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
	}
	
	public static PostagemOutput paraPostagemOutput(Postagem postagem) {
		return new PostagemOutput(postagem.getCodigo(), postagem.getTitulo(), postagem.getCorpo(), postagem.getDataPublicacao(), postagem.getUsuario().getNome());
	}
	
	public static List<PostagemOutput> paraPostagemOutput(List<Postagem> postagens) {
		return postagens.stream().map(postagem -> paraPostagemOutput(postagem)).collect(Collectors.toList());
	}

}
