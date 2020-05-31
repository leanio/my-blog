package dev.leanio.blogapi.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.leanio.blogapi.service.exception.EmailJaCadastradoException;
import dev.leanio.blogapi.service.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class BlogExceptionHandler  extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, "Mensagem inválida", headers, status, request);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, ex.getBindingResult(), headers, status, request);
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
