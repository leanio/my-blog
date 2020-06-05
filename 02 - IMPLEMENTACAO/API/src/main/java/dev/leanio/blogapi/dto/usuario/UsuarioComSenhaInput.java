package dev.leanio.blogapi.dto.usuario;

import javax.validation.constraints.NotBlank;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.dto.exception.ConfirmacaoDaSenhaIncorretaException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String confirmacaoSenha;
	
	public Usuario paraUsuario() {
		if (!senha.equals(confirmacaoSenha)) {
			throw new ConfirmacaoDaSenhaIncorretaException();
		}
		
		return new Usuario(getEmail(), senha, getNome());
	}
	
}
