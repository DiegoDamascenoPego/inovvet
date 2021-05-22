package com.inovvet.core.repository.cadastro.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.categoria.Categoria;
import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoFiltroDTO;
import com.inovvet.core.entity.departamento.Departamento;
import com.inovvet.core.entity.grupo.Grupo;
import com.inovvet.core.entity.subgrupo.SubGrupo;

@Repository
public class ClassificacaoRepositoryImpl implements ClassificacaoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private Predicate addIn(Set<EntityDTO> lista, Root<Classificacao> root, CriteriaQuery<Classificacao> criteriaQuery,
			CriteriaBuilder criteriaBuilder, String entityName) {

		if (lista != null) {
			if (!lista.isEmpty()) {
				In<Integer> inClause = criteriaBuilder.in(root.get(entityName));
				lista.forEach(entity -> {
					inClause.value(entity.getId());
				});

				return criteriaBuilder.and(inClause);

			}

		}
		return null;
	}

	@Override
	public Page<Classificacao> filtrarClassificacao(ClassificacaoFiltroDTO dto, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Classificacao> criteriaQuery = criteriaBuilder.createQuery(Classificacao.class);

		Root<Classificacao> root = criteriaQuery.from(Classificacao.class);
		Join<Classificacao, Departamento> departamento = root.join("departamento");
		Join<Classificacao, Categoria> categoria = root.join("categoria");
		Join<Classificacao, Grupo> grupo = root.join("grupo");
		Join<Classificacao, SubGrupo> subgrupo = root.join("subgrupo");

		Predicate pAtivo = criteriaBuilder.and(criteriaBuilder.equal(root.get("ativo"), 1));
		Predicate pDep = addIn(dto.getDepartamentos(), root, criteriaQuery, criteriaBuilder, "departamento");
		Predicate pCat = addIn(dto.getCategorias(), root, criteriaQuery, criteriaBuilder, "categoria");
		Predicate pGrupo = addIn(dto.getGrupos(), root, criteriaQuery, criteriaBuilder, "grupo");
		Predicate pSub = addIn(dto.getSubgrupos(), root, criteriaQuery, criteriaBuilder, "subgrupo");

		Predicate finalP = pAtivo;

		if (pDep != null) {
			finalP = criteriaBuilder.and(finalP, pDep);
		}

		if (pCat != null) {
			finalP = criteriaBuilder.and(finalP, pCat);
		}

		if (pGrupo != null) {
			finalP = criteriaBuilder.and(finalP, pGrupo);
		}

		if (pSub != null) {
			finalP = criteriaBuilder.and(finalP, pSub);
		}

		criteriaQuery.select(root).where(finalP);
		criteriaQuery.orderBy
			   (criteriaBuilder.asc(departamento.get("nome")), 
				criteriaBuilder.asc(categoria.get("nome")),
				criteriaBuilder.asc(grupo.get("nome")), 
				criteriaBuilder.asc(subgrupo.get("nome")));

		TypedQuery<Classificacao> query = entityManager.createQuery(criteriaQuery);
		Integer count = query.getResultList().size();

		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		Page<Classificacao> result = new PageImpl<Classificacao>(query.getResultList(), pageable, count);

		return result;
	}

}
