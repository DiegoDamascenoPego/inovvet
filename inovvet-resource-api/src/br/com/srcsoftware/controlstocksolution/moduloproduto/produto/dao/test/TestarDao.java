package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.ProdutoDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarDao{

	@Test
	public void executar() throws BackendException {
		/** Inserir com base no componente */
		HibernateConnection hibernate = new HibernateConnection();
		try {

			/** Testando o Componente de Conexão no Contexto do Produto */
			ProdutoDAO dao = new ProdutoDAO();

			ProdutoPO po = new ProdutoPO();
			po.setNome( "Coca Cola" );
			po.setPreco( BigDecimal.valueOf( 2.50 ) );
			//			po.setUnidadeMedida( "LT" );
			po.setDataHoraCadastrado( LocalDateTime.now() );
			po.setCategoria( criarCategoria() );

			hibernate.iniciarTransicao();

			po = (ProdutoPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando Com base no Componente */
			hibernate.iniciarTransicao();

			po.setNome( "Pepsi Cola" );

			hibernate.Alterar( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Testando o Filtrar por Id */

			AbsctractPO encontrado = hibernate.filtrarPorId( Long.valueOf( 1 ), ProdutoPO.class );
			System.out.println( "###### FILTRAR POR ID" + encontrado.toString() );

			/** Testando o Filtrar */

			ProdutoPO poFiltrar = new ProdutoPO();
			poFiltrar.setNome( "Cola" );

			List< ProdutoPO > encontrados = dao.filtrar( poFiltrar );

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

	private CategoriaPO criarCategoria() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();
		CategoriaPO po = new CategoriaPO();
		po.setNome( "Bebidas" );
		po.setDataHoraCadastrado( LocalDateTime.now() );

		hibernate.iniciarTransicao();

		po = (CategoriaPO) hibernate.inserir( po );
		System.out.println( po );

		hibernate.commitTransacao();

		return po;
	}

}
