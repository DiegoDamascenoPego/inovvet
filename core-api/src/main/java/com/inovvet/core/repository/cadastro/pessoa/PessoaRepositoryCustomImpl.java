package com.inovvet.core.repository.cadastro.pessoa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.inovvet.app.util.MappingUtil;
import com.inovvet.core.entity.pessoa.PessoaEntity;
import com.inovvet.core.entity.pessoa.dto.PessoaPesquisaDTO;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;

public class PessoaRepositoryCustomImpl implements PessoaRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private MappingUtil mapper;

	private CriteriaBuilder criteriaBuilder;
	
	private Root<PessoaEntity> root;
	
	private CriteriaQuery<PessoaEntity> criteriaQuery;
	
	private void inicializar() {
		criteriaBuilder = entityManager.getCriteriaBuilder();

		criteriaQuery = criteriaBuilder.createQuery(PessoaEntity.class);

		root = criteriaQuery.from(PessoaEntity.class);
	}
	
	@Override
	public List<PessoaPesquisaDTO> pesquisaCompleta(String valor, boolean ativo,
			EnumFinalidadePessoa tipo) {

		inicializar();

		Predicate pAtivo = criteriaBuilder.and(criteriaBuilder.equal(root.get("ativo"), ativo));
		Predicate pFinalidade = criteriaBuilder.and(criteriaBuilder.equal(root.get("tipo"), tipo));
		Predicate pFantasia = criteriaBuilder.like(criteriaBuilder.upper(root.get("nomeFantasia")), "%" + valor.toUpperCase() + "%");
		Predicate pRazaoSocial = criteriaBuilder.like(criteriaBuilder.upper(root.get("razaoSocial")), "%" + valor.toUpperCase() + "%");

		Predicate finalP = pAtivo;
		finalP = criteriaBuilder.and(finalP, pFinalidade);

		if (!valor.isEmpty()) {
			Predicate pPesquisa = criteriaBuilder.or(pFantasia, pRazaoSocial);
			finalP = criteriaBuilder.and(finalP, pPesquisa);

		}

		this.criteriaQuery.select(root).where(finalP);

		TypedQuery<PessoaEntity> query = entityManager.createQuery(criteriaQuery);

		return mapper.map(query.getResultList(), PessoaPesquisaDTO.class);
	}

	@Override
	public List<PessoaPesquisaDTO> findByDocumentoContainsAndAtivo(String documento, boolean ativo, EnumFinalidadePessoa tipo) {
		inicializar();

		Predicate pAtivo = criteriaBuilder.and(criteriaBuilder.equal(root.get("ativo"), ativo));
		Predicate pFinalidade = criteriaBuilder.and(criteriaBuilder.equal(root.get("tipo"), tipo));
		Predicate pDoc = criteriaBuilder.like(root.get("documento"), "%" + documento + "%");
		
		Predicate finalP = pAtivo;
		finalP = criteriaBuilder.and(finalP, pFinalidade);
		finalP = criteriaBuilder.and(finalP, pDoc);
		
		this.criteriaQuery.select(root).where(finalP);

		TypedQuery<PessoaEntity> query = entityManager.createQuery(criteriaQuery);

		return mapper.map(query.getResultList(), PessoaPesquisaDTO.class);
	}

}
