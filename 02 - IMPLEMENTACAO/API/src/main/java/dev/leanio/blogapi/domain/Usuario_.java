package dev.leanio.blogapi.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Long> codigo;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SingularAttribute<Usuario, Grupo> grupo;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile ListAttribute<Usuario, Postagem> postagens;
	public static volatile ListAttribute<Usuario, Comentario> comentarios;
	public static volatile SingularAttribute<Usuario, String> email;

	public static final String SENHA = "senha";
	public static final String CODIGO = "codigo";
	public static final String ATIVO = "ativo";
	public static final String GRUPO = "grupo";
	public static final String NOME = "nome";
	public static final String POSTAGENS = "postagens";
	public static final String COMENTARIOS = "comentarios";
	public static final String EMAIL = "email";

}

