package dev.leanio.blogapi.dto.comentario;

import java.util.List;
import java.util.stream.Collectors;

import dev.leanio.blogapi.domain.Comentario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioOutput {
	
	private Long codigo;
	
	private String texto;

	public ComentarioOutput(Comentario comentario) {
		this.codigo = comentario.getCodigo();
		this.texto = comentario.getTexto();
	}
	
	public static ComentarioOutput paraComentarioOutput(Comentario comentario) {
		return new ComentarioOutput(comentario);
	}
	
	public static List<ComentarioOutput> paraComentarioOutput(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> paraComentarioOutput(comentario)).collect(Collectors.toList());
	}

}
