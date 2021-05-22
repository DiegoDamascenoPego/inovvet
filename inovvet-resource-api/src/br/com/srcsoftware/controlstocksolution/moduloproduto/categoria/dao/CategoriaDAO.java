package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class CategoriaDAO{

	public void inserir( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {
		/** Invocando o método inserir do nosso componente de Conexão Hiberante */
		CategoriaPO poInserido = (CategoriaPO) hibernate.inserir( po );
		po.setId( poInserido.getId() );

	}

	public void alterar( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {
		/** Invocando o método Alterar do nosso componente de Conexão Hiberante */
		hibernate.Alterar( po );

	}

	public void excluir( HibernateConnection hibernate, CategoriaPO po ) throws BackendException {
		/** Invocando o método Excluir do nosso componente de Conexão Hiberante */
		hibernate.Excluir( po );

	}

	public CategoriaPO filtrarPorId( Long id ) throws BackendException {
		/** Invocando o método FiltrarPorId do nosso componente de Conexão Hiberante */
		return (CategoriaPO) new HibernateConnection().filtrarPorId( id, CategoriaPO.class );
	}

	public List< CategoriaPO > filtrar( CategoriaPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();

			//Fazer oque tem que Fazer

			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( CategoriaPO.class );

			// Definindo o From

			Root root = criteria.from( CategoriaPO.class );

			//Deixando a Criteria Preparada para a consulta
			criteria.select( root );

			// definindo os Predicates para consulta (WHERE)

			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {

				if ( poFiltrar.getNome() != null && !poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
			}

			// Adicionando o Predicate no where
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< CategoriaPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( BackendException e ) {
			hibernate.rollBackTransacao();
			throw e;
		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Filtrar", e );
		}

	}

}
