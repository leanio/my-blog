package dev.leanio.blogapi.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.dto.comentario.ComentarioInput;
import dev.leanio.blogapi.dto.comentario.ComentarioOutput;
import dev.leanio.blogapi.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioResource {
	
	@Autowired
	private ComentarioService comentarioService;

	@PostMapping
	public ResponseEntity<ComentarioOutput> adicionar(@Valid @RequestBody ComentarioInput comentario) {
		Comentario comentarioSalvo = comentarioService.adicionar(comentario.paraComentario());
		return ResponseEntity.status(HttpStatus.CREATED).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ComentarioOutput> atualizar(@PathVariable Long codigo, @Valid @RequestBody ComentarioInput comentario) {
		Comentario comentarioSalvo = comentarioService.atualizar(codigo, comentario.paraComentario());
		return ResponseEntity.status(HttpStatus.OK).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ComentarioOutput> buscarPeloCodigo(@PathVariable Long codigo) {
		Comentario comentarioSalvo = comentarioService.buscarPeloCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(ComentarioOutput.paraComentarioOutput(comentarioSalvo));
	}
}
