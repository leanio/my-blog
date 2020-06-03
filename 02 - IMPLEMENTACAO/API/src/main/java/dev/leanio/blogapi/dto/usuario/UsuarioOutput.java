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

	public UsuarioOutput(Long codigo, String email, String nome, Boolean ativo, Grupo grupo) {
		this.codigo = codigo;
		this.email = email;
		this.nome = nome;
		this.grupo = grupo;
		this.ativo = ativo;
	}
	
	public static UsuarioOutput paraUsuarioOutput(Usuario usuario) {
		return new UsuarioOutput(usuario.getCodigo(), usuario.getEmail(), usuario.getNome(), usuario.getAtivo(), usuario.getGrupo());
	}
	
	public static List<UsuarioOutput> paraUsuarioOutput(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> paraUsuarioOutput(usuario)).collect(Collectors.toList());
	}
	
}
