package dev.leanio.blogapi.dto.comentario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInput {
	
	@NotBlank
	private String texto;
	
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private Postagem postagem;
	
	public Comentario paraComentario() {
		return new Comentario(texto, usuario, postagem);
	}

}
