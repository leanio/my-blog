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
import dev.leanio.blogapi.dto.usuario.UsuarioComSenhaInput;
import dev.leanio.blogapi.dto.usuario.UsuarioInput;
import dev.leanio.blogapi.dto.usuario.UsuarioOutput;
import dev.leanio.blogapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioOutput> adicionar(@Valid @RequestBody UsuarioComSenhaInput usuario) {
		Usuario usuarioSalvo = usuarioService.adicionar(usuario.paraUsuario());
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioOutput.paraUsuarioOutput(usuarioSalvo));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<UsuarioOutput> atualizar(@PathVariable Long codigo, @Valid @RequestBody UsuarioInput usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario.paraUsuario());
		return ResponseEntity.status(HttpStatus.OK).body(UsuarioOutput.paraUsuarioOutput(usuarioSalvo));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<UsuarioOutput> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuarioSalvo = usuarioService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(UsuarioOutput.paraUsuarioOutput(usuarioSalvo));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {

	}
	
}
