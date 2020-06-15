package dev.leanio.blogapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.repository.UsuarioRepository;
import dev.leanio.blogapi.service.exception.EmailJaCadastradoException;
import dev.leanio.blogapi.service.exception.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario adicionar(Usuario usuario) {
		configurarUsuario(usuario);
		usuario.setCodigo(null);
		usuario.setAtivo(true);

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		usuario.setSenha(bc.encode(usuario.getSenha()));

		validarNovoUsuario(usuario);

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Long codigo, Usuario usuario) {
		configurarUsuario(usuario);

		validarAtualizacaoUsuario(codigo, usuario);

		Usuario usuarioSalvo = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo", "ativo", "senha", "grupo");

		return usuarioRepository.save(usuarioSalvo);
	}

	public Usuario buscarPeloCodigo(Long codigo) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(codigo);

		return usuarioSalvo.orElseThrow(() -> new UsuarioNaoEncontradoException());
	}

	private void configurarUsuario(Usuario usuario) {
		usuario.setNome(usuario.getNome().toLowerCase().trim());
		usuario.setEmail(usuario.getEmail().toLowerCase().trim());
	}

	private void validarNovoUsuario(Usuario usuario) {
		Usuario usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);

		if (usuarioEmail != null) {
			throw new EmailJaCadastradoException();
		}
	}

	private void validarAtualizacaoUsuario(Long codigo, Usuario usuario) {
		Usuario usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);

		if (usuarioEmail != null) {
			if (!usuarioEmail.equals(codigo)) {
				throw new EmailJaCadastradoException();
			}
		}
	}

}
