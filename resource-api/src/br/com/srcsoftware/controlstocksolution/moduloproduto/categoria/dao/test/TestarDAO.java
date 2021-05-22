package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.CategoriaDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarDAO{

	@Test
	public void executar() throws BackendException {
		/** Inserir com base no componente */
		HibernateConnection hibernate = new HibernateConnection();
		try {

			/** Testando o Componente de Conexão no Contexto do categoria */
			CategoriaDAO dao = new CategoriaDAO();

			CategoriaPO po = new CategoriaPO();
			po.setNome( "Bebidas" );
			po.setDataHoraCadastrado( LocalDateTime.now() );

			hibernate.iniciarTransicao();

			po = (CategoriaPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando Com base no Componente */
			hibernate.iniciarTransicao();

			po.setNome( "Bebidas Alteradas" );

			hibernate.Alterar( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Testando o Filtrar por Id */

			AbsctractPO encontrado = hibernate.filtrarPorId( Long.valueOf( 1 ), CategoriaPO.class );
			System.out.println( encontrado.toString() );

			/** Testando o Filtrar */

			CategoriaPO poFiltrar = new CategoriaPO();
			poFiltrar.setNome( "Bebidas" );

			List< CategoriaPO > encontrados = dao.filtrar( poFiltrar );

			System.out.println( "############# FILTRAR " + encontrados );

			/** excluindo Com base no Componente */
			hibernate.iniciarTransicao();

			hibernate.Excluir( po );

			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			e.printStackTrace();
			hibernate.rollBackTransacao();
		}

	}

}
