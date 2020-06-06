package dev.leanio.blogapi.repository.postagem;

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
import org.springframework.util.StringUtils;

import dev.leanio.blogapi.domain.Postagem;
import dev.leanio.blogapi.domain.Postagem_;
import dev.leanio.blogapi.dto.postagem.PostagemOutput;
import dev.leanio.blogapi.repository.filter.PostagemFilter;

public class PostagemRepositoryImpl implements PostagemRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<PostagemOutput> filtrar(PostagemFilter postagemFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PostagemOutput> criteria = builder.createQuery(PostagemOutput.class);
		Root<Postagem> root = criteria.from(Postagem.class);
		
		Predicate[] predicates = criarRestricoes(postagemFilter, builder, root);
		
		criteria.select(builder.construct(PostagemOutput.class, root));
		criteria.where(predicates);

		TypedQuery<PostagemOutput> query = entityManager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(postagemFilter));
	}
	
	private Predicate[] criarRestricoes(PostagemFilter postagemFilter, CriteriaBuilder builder, Root<Postagem> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(postagemFilter.getTitulo())) {
			predicates.add(builder.like(builder.lower(root.get(Postagem_.titulo)), "%" + postagemFilter.getTitulo().toLowerCase() + "%"));
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
	
	private Long total(PostagemFilter postagemFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Postagem> root = criteria.from(Postagem.class);

		Predicate[] predicates = criarRestricoes(postagemFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		TypedQuery<Long> query = entityManager.createQuery(criteria);
		
		return query.getSingleResult();
	}
	
}
