package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.dao.test;

import org.junit.Test;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.dao.UnidadeDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarDao{
	@Test
	public void executar() throws BackendException {
		/** Inserir com base no componente */
		HibernateConnection hibernate = new HibernateConnection();
		try {

			UnidadeDAO dao = new UnidadeDAO();
			UnidadePO unidade = new UnidadePO();

			unidade.setNome( "Caixa" );
			unidade.setSigla( "CX" );

			hibernate.iniciarTransicao();

			dao.inserir( hibernate, unidade );

			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			e.printStackTrace();
			hibernate.rollBackTransacao();
		}
	}
}
