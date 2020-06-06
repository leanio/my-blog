package dev.leanio.blogapi.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Postagem.class)
public abstract class Postagem_ {

	public static volatile SingularAttribute<Postagem, Long> codigo;
	public static volatile SingularAttribute<Postagem, String> corpo;
	public static volatile SingularAttribute<Postagem, String> titulo;
	public static volatile SingularAttribute<Postagem, LocalDate> dataPublicacao;
	public static volatile SingularAttribute<Postagem, Usuario> usuario;
	public static volatile ListAttribute<Postagem, Comentario> comentarios;

	public static final String CODIGO = "codigo";
	public static final String CORPO = "corpo";
	public static final String TITULO = "titulo";
	public static final String DATA_PUBLICACAO = "dataPublicacao";
	public static final String USUARIO = "usuario";
	public static final String COMENTARIOS = "comentarios";

}

