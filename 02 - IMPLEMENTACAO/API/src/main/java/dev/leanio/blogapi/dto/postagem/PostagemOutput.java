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
	
	public PostagemOutput(Postagem postagem) {
		this.codigo = postagem.getCodigo();
		this.titulo = postagem.getTitulo();
		this.corpo = postagem.getCorpo();
		this.dataPublicacao = postagem.getDataPublicacao();
		this.autor = postagem.getUsuario().getNome();
	}
	
	public static PostagemOutput paraPostagemOutput(Postagem postagem) {
		return new PostagemOutput(postagem);
	}
	
	public static List<PostagemOutput> paraPostagemOutput(List<Postagem> postagens) {
		return postagens.stream().map(postagem -> paraPostagemOutput(postagem)).collect(Collectors.toList());
	}

}
