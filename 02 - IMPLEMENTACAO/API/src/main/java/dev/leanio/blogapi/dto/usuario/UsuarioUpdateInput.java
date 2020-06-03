package dev.leanio.blogapi.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import dev.leanio.blogapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateInput {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	public Usuario paraUsuario() {
		return new Usuario(email, nome);
	}
	
}
