package dev.leanio.blogapi.dto.usuario;

import java.util.List;
import java.util.stream.Collectors;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.domain.enumeration.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutput {
	
	private Long codigo;
	
	private String email;

	private String nome;
	
	private TipoUsuario tipo;

	public UsuarioOutput(Long codigo, String email, String nome, TipoUsuario tipo) {
		this.codigo = codigo;
		this.email = email;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public static UsuarioOutput paraUsuarioOutput(Usuario usuario) {
		return new UsuarioOutput(usuario.getCodigo(), usuario.getEmail(), usuario.getNome(), usuario.getTipo());
	}
	
	public static List<UsuarioOutput> paraUsuarioOutput(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> paraUsuarioOutput(usuario)).collect(Collectors.toList());
	}
	
}
