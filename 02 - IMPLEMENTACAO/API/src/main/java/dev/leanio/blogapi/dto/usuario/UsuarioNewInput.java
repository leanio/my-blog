package dev.leanio.blogapi.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.dto.exception.ConfirmacaoDaSenhaIncorretaException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioNewInput {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String confirmacaoSenha;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Boolean ativo;
	
	public Usuario paraUsuario() {
		if (!senha.equals(confirmacaoSenha)) {
			throw new ConfirmacaoDaSenhaIncorretaException();
		}
		
		return new Usuario(email, senha, nome, ativo);
	}
	
}
