package dev.leanio.blogapi.repository.comentario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import dev.leanio.blogapi.domain.Comentario;
import dev.leanio.blogapi.domain.Comentario_;
import dev.leanio.blogapi.domain.Postagem_;
import dev.leanio.blogapi.dto.comentario.ComentarioOutput;
import dev.leanio.blogapi.repository.filter.ComentarioFilter;

public class ComentarioRepositoryImpl implements ComentarioRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<ComentarioOutput> filtrar(ComentarioFilter comentarioFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ComentarioOutput> criteria = builder.createQuery(ComentarioOutput.class);
		Root<Comentario> root = criteria.from(Comentario.class);
		
		Predicate[] predicates = criarRestricoes(comentarioFilter, builder, root);
		
		criteria.select(builder.construct(ComentarioOutput.class, root));
		criteria.where(predicates);

		TypedQuery<ComentarioOutput> query = entityManager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(comentarioFilter));
	}
	
	private Predicate[] criarRestricoes(ComentarioFilter comentarioFilter, CriteriaBuilder builder, Root<Comentario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (comentarioFilter.getCodigoPostagem() != null) {
			predicates.add(builder.equal(root.get(Comentario_.postagem), comentarioFilter.getCodigoPostagem()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
	
	private Long total(ComentarioFilter comentarioFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Comentario> root = criteria.from(Comentario.class);

		Predicate[] predicates = criarRestricoes(comentarioFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		TypedQuery<Long> query = entityManager.createQuery(criteria);
		
		return query.getSingleResult();
	}
	
}
