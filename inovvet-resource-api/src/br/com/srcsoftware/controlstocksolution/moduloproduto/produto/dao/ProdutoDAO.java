package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public final class ProdutoDAO{

	public void inserir( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {
		/** Invocando o método inserir do nosso componente de Conexão Hiberante */
		ProdutoPO poInserido = (ProdutoPO) hibernate.inserir( po );
		po.setId( poInserido.getId() );

	}

	public void alterar( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {
		/** Invocando o método Alterar do nosso componente de Conexão Hiberante */
		hibernate.Alterar( po );

	}

	public void excluir( HibernateConnection hibernate, ProdutoPO po ) throws BackendException {
		/** Invocando o método Excluir do nosso componente de Conexão Hiberante */
		hibernate.Excluir( po );

	}

	public ProdutoPO filtrarPorId( Long id ) throws BackendException {
		/** Invocando o método FiltrarPorId do nosso componente de Conexão Hiberante */
		return (ProdutoPO) new HibernateConnection().filtrarPorId( id, ProdutoPO.class );
	}

	public List< ProdutoPO > filtrar( ProdutoPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();

			//Fazer oque tem que Fazer

			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( ProdutoPO.class );

			// Definindo o From

			Root root = criteria.from( ProdutoPO.class );

			//Deixando a Criteria Preparada para a consulta
			criteria.select( root );

			// definindo os Predicates para consulta (WHERE)

			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {

				if ( poFiltrar.getNome() != null && !poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}

				if ( poFiltrar.getPreco() != null ) {
					Predicate precoParam = builder.equal( root.get( "preco" ), poFiltrar.getPreco() );
					predicados.add( precoParam );
				}

				//				if ( poFiltrar.getUnidadeMedida() != null && !poFiltrar.getUnidadeMedida().isEmpty() ) {
				//					Predicate unidadeParam = builder.like( root.get( "unidadeMedida" ), poFiltrar.getUnidadeMedida() );
				//					predicados.add( unidadeParam );
				//				}

				/** Join unidade */
				Join< ProdutoPO, UnidadePO > joinUnidade = root.join( "unidadeMedida", JoinType.INNER );

				if ( poFiltrar.getUnidadeMedida().getNome() != null && !poFiltrar.getUnidadeMedida().getNome().isEmpty() ) {
					Predicate nomeUnidadeParam = builder.like( joinUnidade.get( "nome" ), poFiltrar.getUnidadeMedida().getNome().concat( "%" ) );
					predicados.add( nomeUnidadeParam );
				}

				if ( poFiltrar.getUnidadeMedida().getId() != null ) {
					Predicate idUnidadeParam = builder.equal( joinUnidade.get( "id" ), poFiltrar.getUnidadeMedida().getId() );
					predicados.add( idUnidadeParam );
				}

				/** Join categoria */
				Join< ProdutoPO, CategoriaPO > joinCateoria = root.join( "categoria", JoinType.INNER );

				if ( poFiltrar.getCategoria().getNome() != null && !poFiltrar.getCategoria().getNome().isEmpty() ) {
					Predicate nomeCategoriaParam = builder.like( joinCateoria.get( "nome" ), poFiltrar.getCategoria().getNome().concat( "%" ) );
					predicados.add( nomeCategoriaParam );
				}

				if ( poFiltrar.getCategoria().getId() != null ) {
					Predicate idCategoriaParam = builder.equal( joinCateoria.get( "id" ), poFiltrar.getCategoria().getId() );
					predicados.add( idCategoriaParam );
				}
			}

			// Adicionando o Predicate no where
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< ProdutoPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

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
