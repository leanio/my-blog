package dev.leanio.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.leanio.blogapi.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
