package dev.leanio.blogapi.security.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.repository.UsuarioRepository;

@Service
public class BlogUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional  = usuarioRepository.findByEmail(email);
		
		Usuario usuario = usuarioOptional.orElseThrow(()->new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		usuario.getGrupo().getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getNome().toUpperCase())));
		return authorities;
	}

}
