package dev.leanio.blogapi.security.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.dto.comentario.ComentarioInput;
import dev.leanio.blogapi.repository.ComentarioRepository;
import dev.leanio.blogapi.repository.UsuarioRepository;
import dev.leanio.blogapi.service.exception.UsuarioNaoEncontradoException;

@Component
public class BlogSecurity {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public boolean podeCadastrarComentario() {
		return hasAuthority("ROLE_CADASTRAR_COMENTARIO");
	}
	
	public boolean podeAtualizarComentario(Long codigo) {
		return hasAuthority("ROLE_ATUALIZAR_COMENTARIO") && podeGerenciarComentario(codigo);
	}
	
	public boolean podeRemoverComentario(Long codigo) {
		return hasAuthority("ROLE_REMOVER_COMENTARIO") && podeGerenciarComentario(codigo);
	}
	
	private boolean podeGerenciarComentario(Long codigo) {
		Comentario comentario = comentarioRepository.getOne(codigo);
		return getUsuarioAutenticado().equals(comentario.getUsuario());
	}

	private String getEmailAutenticado() {
		return getAuthentication().getName();
	}
	
	private Usuario getUsuarioAutenticado() {
		return usuarioRepository.findByEmail(getEmailAutenticado()).orElseThrow(() -> new UsuarioNaoEncontradoException());
	}

}
