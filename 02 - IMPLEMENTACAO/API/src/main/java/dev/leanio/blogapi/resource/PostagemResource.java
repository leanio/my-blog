package dev.leanio.blogapi.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import dev.leanio.blogapi.dto.postagem.PostagemInput;
import dev.leanio.blogapi.dto.postagem.PostagemOutput;
import dev.leanio.blogapi.repository.PostagemRepository;
import dev.leanio.blogapi.repository.filter.PostagemFilter;
import dev.leanio.blogapi.service.PostagemService;

@RestController
@RequestMapping("/postagens")
public class PostagemResource {
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@PostMapping
	public ResponseEntity<PostagemOutput> adicionar(@Valid @RequestBody PostagemInput postagem) {
		Postagem postagemSalva = postagemService.adicionar(postagem.paraPostagem());
		return ResponseEntity.status(HttpStatus.CREATED).body(PostagemOutput.paraPostagemOutput(postagemSalva));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<PostagemOutput> atualizar(@PathVariable Long codigo, @Valid @RequestBody PostagemInput postagem) {
		Postagem postagemSalva = postagemService.atualizar(codigo, postagem.paraPostagem());
		return ResponseEntity.status(HttpStatus.OK).body(PostagemOutput.paraPostagemOutput(postagemSalva));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<PostagemOutput> buscarPeloCodigo(@PathVariable Long codigo) {
		Postagem postagemSalva = postagemService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(PostagemOutput.paraPostagemOutput(postagemSalva));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {

	}
	
	@GetMapping
	public Page<PostagemOutput> filtrar(PostagemFilter postagemFilter, Pageable pageable) {
		return postagemRepository.filtrar(postagemFilter, pageable);
	}

}
