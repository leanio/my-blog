package dev.leanio.blogapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.leanio.blogapi.domain.enumeration.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String email;
	
	private String senha;
	
	private String nome;
	
	private Boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@ManyToMany
	private List<Permissao> permissoes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Postagem> postagens;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentarios;
	
	public Usuario() {
		
	}

	public Usuario(String email, String senha, String nome, Boolean ativo, TipoUsuario tipo) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.ativo = ativo;
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
