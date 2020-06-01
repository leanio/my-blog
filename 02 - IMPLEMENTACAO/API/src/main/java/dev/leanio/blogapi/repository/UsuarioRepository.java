package dev.leanio.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.leanio.blogapi.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByEmail(String email);

}
