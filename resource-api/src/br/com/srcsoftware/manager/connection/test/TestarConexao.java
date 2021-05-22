package br.com.srcsoftware.manager.connection.test;

import org.junit.Test;

import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarConexao{

	@Test
	public void executar() throws BackendException {
		HibernateConnection connection = new HibernateConnection();

		try {
			try {
				connection.iniciarTransicao();

				Object versaoBD = connection.getCurrentSession().createNativeQuery( "select @@version" ).getSingleResult();
				System.out.println( versaoBD.toString() );

				Object dataDB = connection.getCurrentSession().createNativeQuery( "select now()" ).getSingleResult();
				System.out.println( dataDB.toString() );

				// selecionando Categoria com SQL NATIVAS

				Object categoriaSQL = connection.getCurrentSession().createNativeQuery( "select * from categorias" ).getResultList();
				System.out.println( categoriaSQL.toString() );

				// selecionando Categorias com HQL

				Object categoriasHQL = connection.getCurrentSession().createQuery( "select c from CategoriaPO c" ).getResultList();
				System.out.println( categoriasHQL );

				connection.commitTransacao();

			} catch ( BackendException e ) {
				e.printStackTrace();

				connection.rollBackTransacao();
			}
		} finally {
			connection.destroy();
		}

	}

}
