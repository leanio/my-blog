package dev.leanio.blogapi.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.domain.enumeration.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Boolean ativo;
	
	@NotNull
	private TipoUsuario tipo;
	
	public Usuario paraUsuario() {
		return new Usuario(email, senha, nome, ativo, tipo);
	}
}
