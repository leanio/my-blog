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

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.dto.comentario.ComentarioInput;
import dev.leanio.blogapi.dto.comentario.ComentarioOutput;
import dev.leanio.blogapi.dto.postagem.PostagemOutput;
import dev.leanio.blogapi.repository.ComentarioRepository;
import dev.leanio.blogapi.repository.filter.ComentarioFilter;
import dev.leanio.blogapi.repository.filter.PostagemFilter;
import dev.leanio.blogapi.security.authorization.CheckSecurity;
import dev.leanio.blogapi.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioResource {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	@PostMapping
	@CheckSecurity.Comentarios.PodeCadastrar
	public ResponseEntity<ComentarioOutput> adicionar(@Valid @RequestBody ComentarioInput comentario) {
		Comentario comentarioSalvo = comentarioService.adicionar(comentario.paraComentario());
		return ResponseEntity.status(HttpStatus.CREATED).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
	
	@PutMapping("/{codigo}")
	@CheckSecurity.Comentarios.PodeAtualizar
	public ResponseEntity<ComentarioOutput> atualizar(@PathVariable Long codigo, @Valid @RequestBody ComentarioInput comentario) {
		Comentario comentarioSalvo = comentarioService.atualizar(codigo, comentario.paraComentario());
		return ResponseEntity.status(HttpStatus.OK).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ComentarioOutput> buscarPeloCodigo(@PathVariable Long codigo) {
		Comentario comentarioSalvo = comentarioService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Comentarios.PodeRemover
	public void remover(@PathVariable Long codigo) {
		comentarioRepository.deleteById(codigo);
	}
	
	@GetMapping
	public Page<ComentarioOutput> filtrar(ComentarioFilter comentarioFilter, Pageable pageable) {
		return comentarioRepository.filtrar(comentarioFilter, pageable);
	}
}
