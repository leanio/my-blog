package dev.leanio.blogapi.dto.usuario;

import java.util.List;
import java.util.stream.Collectors;

import dev.leanio.blogapi.domain.Grupo;
import dev.leanio.blogapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutput {
	
	private Long codigo;
	
	private String email;

	private String nome;
	
	private Boolean ativo;
	
	private Grupo grupo;

	public UsuarioOutput(Usuario usuario) {
		this.codigo = usuario.getCodigo();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.grupo = usuario.getGrupo();
		this.ativo = usuario.getAtivo();
	}
	
	public static UsuarioOutput paraUsuarioOutput(Usuario usuario) {
		return new UsuarioOutput(usuario);
	}
	
	public static List<UsuarioOutput> paraUsuarioOutput(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> paraUsuarioOutput(usuario)).collect(Collectors.toList());
	}
	
}
