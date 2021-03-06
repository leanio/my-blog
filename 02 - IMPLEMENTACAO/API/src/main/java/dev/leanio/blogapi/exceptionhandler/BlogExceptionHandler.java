package dev.leanio.blogapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.leanio.blogapi.dto.exception.ConfirmacaoDaSenhaIncorretaException;
import dev.leanio.blogapi.service.exception.ComentarioNaoEncontradoException;
import dev.leanio.blogapi.service.exception.EmailJaCadastradoException;
import dev.leanio.blogapi.service.exception.PostagemComTituloJaCadastradoException;
import dev.leanio.blogapi.service.exception.PostagemNaoEncontradaException;
import dev.leanio.blogapi.service.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class BlogExceptionHandler  extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmailJaCadastradoException.class })
	public ResponseEntity<Object> handlerEmailJaCadastradoException(EmailJaCadastradoException ex) {
		String mensagemUsuario = messageSource.getMessage("usuario.email-ja-cadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	@ExceptionHandler({ UsuarioNaoEncontradoException.class })
	public ResponseEntity<Object> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
		String mensagemUsuario = messageSource.getMessage("usuario.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	@ExceptionHandler({ PostagemComTituloJaCadastradoException.class })
	public ResponseEntity<Object> handlerPostagemComTituloJaCadastradoException(PostagemComTituloJaCadastradoException ex) {
		String mensagemUsuario = messageSource.getMessage("postagem.titulo-ja-cadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	@ExceptionHandler({ PostagemNaoEncontradaException.class })
	public ResponseEntity<Object> handlerPostagemNaoEncontradaException(PostagemNaoEncontradaException ex) {
		String mensagemUsuario = messageSource.getMessage("postagem.nao-encontrada", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	@ExceptionHandler({ ConfirmacaoDaSenhaIncorretaException.class })
	public ResponseEntity<Object> handlerConfirmacaoDeSenhaIncorretaException(ConfirmacaoDaSenhaIncorretaException ex) {
		String mensagemUsuario = messageSource.getMessage("usuario.confirmacao-da-senha-incorreta", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	@ExceptionHandler({ ComentarioNaoEncontradoException.class })
	public ResponseEntity<Object> handlerComentarioNaoEncontradoException(ComentarioNaoEncontradoException ex) {
		String mensagemUsuario = messageSource.getMessage("comentario.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro(mensagemUsuario, mensagemDesenvolvedor));
	}
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();

			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}

		return erros;
	}
	
	public static class Erro {
		private String mensagemUsuario;
		
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
	}
	
}
