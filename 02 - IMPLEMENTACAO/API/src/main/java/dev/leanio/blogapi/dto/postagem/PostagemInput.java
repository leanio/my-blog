package dev.leanio.blogapi.dto.postagem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemInput {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String corpo;
	
	@NotNull
	private Usuario usuario;
	
	public Postagem paraPostagem() {
		return new Postagem(titulo, corpo, usuario);
	}
	
}
