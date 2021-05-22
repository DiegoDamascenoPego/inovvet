package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.dao.UnidadeDAO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.manager.utilidades.Utilidades;

public class UnidadeSERVICE implements Crud{

	private final UnidadeDAO DAO;

	public UnidadeSERVICE(){
		DAO = new UnidadeDAO();
	}

	@Override
	public void inserir( final AbsctractPO po ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransicao();
			if ( po == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			UnidadePO unidadePO = null;

			if ( po instanceof UnidadePO ) {
				unidadePO = (UnidadePO) po;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			if ( !( unidadePO.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "O Nome possui caracteres inválidos" );
			}

			if ( !( unidadePO.getSigla().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "A Sigla com caracteres inválidos." );
			}

			DAO.inserir( hibernate, unidadePO );
			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			hibernate.rollBackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollBackTransacao();
			throw new BackendException( "Erro desconhecimento ao inserir", e );
		}

	}

	@Override
	public void alterar( final AbsctractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();
			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			UnidadePO unidadePO = null;

			if ( PO instanceof UnidadePO ) {
				unidadePO = (UnidadePO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			if ( !( unidadePO.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "O Nome possui caracteres inválidos" );
			}

			if ( !( unidadePO.getSigla().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "A Sigla com caracteres inválidos." );
			}

			DAO.alterar( hibernate, unidadePO );
			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			hibernate.rollBackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollBackTransacao();
			throw new BackendException( "Erro desconhecimento ao alterar", e );
		}

	}

	@Override
	public void excluir( final AbsctractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			UnidadePO unidadePO = null;

			if ( PO instanceof UnidadePO ) {
				unidadePO = (UnidadePO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			DAO.excluir( hibernate, unidadePO );
			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			hibernate.rollBackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollBackTransacao();
			throw new BackendException( "Erro desconhecimento ao Excluir", e );
		}

	}

	@Override
	public List filtrar( final AbsctractPO PO ) throws BackendException {
		try {

			UnidadePO unidadePO = null;

			if ( PO != null ) {
				if ( PO instanceof UnidadePO ) {
					unidadePO = (UnidadePO) PO;
				} else {
					throw new BackendException( "Objeto PO não condiz com o contexto." );
				}
			}

			return DAO.filtrar( unidadePO );

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecimento ao Excluir", e );
		}

	}

	@Override
	public AbsctractPO filtrarPorId( final String id ) throws BackendException {
		try {
			if ( id == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			return DAO.filtrarPorId( Long.valueOf( id ) );

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecimento ao Excluir", e );
		}
	}
}
