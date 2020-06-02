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

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.service.PostagemService;

@RestController
@RequestMapping("/postagens")
public class PostagemResource {
	
	@Autowired
	private PostagemService postagemService;
	
	@PostMapping
	public ResponseEntity<Postagem> adicionar(@Valid @RequestBody Postagem postagem) {
		Postagem postagemSalva = postagemService.adicionar(postagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemSalva);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Postagem> atualizar(@PathVariable Long codigo, @Valid @RequestBody Postagem postagem) {
		Postagem postagemSalva = postagemService.atualizar(codigo, postagem);
		return ResponseEntity.status(HttpStatus.OK).body(postagemSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Postagem> buscarPeloCodigo(@PathVariable Long codigo) {
		Postagem postagemSalva = postagemService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(postagemSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {

	}

}
