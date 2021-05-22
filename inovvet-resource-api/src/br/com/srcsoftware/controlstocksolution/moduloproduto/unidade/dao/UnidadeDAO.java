package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class UnidadeDAO{

	public void inserir( HibernateConnection hibernate, UnidadePO po ) throws BackendException {
		UnidadePO poInserido = (UnidadePO) hibernate.inserir( po );
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, UnidadePO po ) throws BackendException {
		hibernate.Alterar( po );

	}

	public void excluir( HibernateConnection hibernate, UnidadePO po ) throws BackendException {
		hibernate.Excluir( po );

	}

	public List< UnidadePO > filtrar( UnidadePO poFiltrar ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();

			//Fazer oque tem que Fazer

			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( UnidadePO.class );

			// Definindo o From

			Root root = criteria.from( UnidadePO.class );

			//Deixando a Criteria Preparada para a consulta
			criteria.select( root );

			// definindo os Predicates para consulta (WHERE)

			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {

				if ( poFiltrar.getNome() != null && !poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}

				if ( poFiltrar.getSigla() != null ) {
					Predicate siglaParam = builder.like( root.get( "sigla" ), poFiltrar.getSigla().concat( "%" ) );
					predicados.add( siglaParam );
				}

			}

			// Adicionando o Predicate no where
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< UnidadePO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( BackendException e ) {
			hibernate.rollBackTransacao();
			throw e;
		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Filtrar", e );
		}
	}

	public UnidadePO filtrarPorId( Long id ) throws BackendException {
		return (UnidadePO) new HibernateConnection().filtrarPorId( id, UnidadePO.class );
	}

}
