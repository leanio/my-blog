package dev.leanio.blogapi.security.authorization;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Comentarios {

		@PreAuthorize("@blogSecurity.podeCadastrarComentario()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar { }
		
		@PreAuthorize("@blogSecurity.podeAtualizarComentario(#codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar { }
		
		@PreAuthorize("@blogSecurity.podeRemoverComentario(#codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeRemover { }
		
	}

}
