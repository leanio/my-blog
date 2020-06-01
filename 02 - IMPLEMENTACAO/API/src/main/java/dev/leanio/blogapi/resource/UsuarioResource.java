package dev.leanio.blogapi.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.leanio.blogapi.domain.Usuario;
import dev.leanio.blogapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> adicionar(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.adicionar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuarioSalvo = usuarioService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {

	}
	
}
