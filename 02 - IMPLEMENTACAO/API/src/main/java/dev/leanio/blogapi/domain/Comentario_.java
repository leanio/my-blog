package dev.leanio.blogapi.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comentario.class)
public abstract class Comentario_ {

	public static volatile SingularAttribute<Comentario, String> texto;
	public static volatile SingularAttribute<Comentario, Long> codigo;
	public static volatile SingularAttribute<Comentario, LocalDate> dataPublicacao;
	public static volatile SingularAttribute<Comentario, Usuario> usuario;
	public static volatile SingularAttribute<Comentario, Postagem> postagem;

	public static final String TEXTO = "texto";
	public static final String CODIGO = "codigo";
	public static final String DATA_PUBLICACAO = "dataPublicacao";
	public static final String USUARIO = "usuario";
	public static final String POSTAGEM = "postagem";

}

