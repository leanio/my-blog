package dev.leanio.blogapi.dto.postagem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemInput {
	
	@NotBlank
	@Size(max = 40)
	private String titulo;
	
	@NotBlank
	@Size(max = Integer.MAX_VALUE)
	private String corpo;
	
	@NotNull
	private Usuario usuario;
	
	public Postagem paraPostagem() {
		return new Postagem(titulo, corpo, usuario);
	}
	
}
