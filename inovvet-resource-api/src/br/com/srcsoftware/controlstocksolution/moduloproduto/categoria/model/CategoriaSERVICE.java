package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.dao.CategoriaDAO;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * 
 * Classe que representa a camada de negócio da categoria
 * nesta classe são implementadas todas as regras de negócio necessárias para garantir a consistência dos dados contidos no PO e ou/
 * garantir o correto funcionamento do processo solicitado
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 24 de jul de 2018 22:16:44
 * @version 1.0
 */
public final class CategoriaSERVICE implements Crud{

	private final CategoriaDAO DAO;

	public CategoriaSERVICE(){
		DAO = new CategoriaDAO();
	}

	@Override
	public void inserir( final AbsctractPO po ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransicao();
			if ( po == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			CategoriaPO categoriaPO = null;

			if ( po instanceof CategoriaPO ) {
				categoriaPO = (CategoriaPO) po;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			if ( !( categoriaPO.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "O Nome não são permitidos caractéres númericos." );
			}

			DAO.inserir( hibernate, categoriaPO );
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

			CategoriaPO categoriaPO = null;

			if ( PO instanceof CategoriaPO ) {
				categoriaPO = (CategoriaPO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			if ( !( categoriaPO.getNome().matches( Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO ) ) ) {
				throw new BackendException( "O Nome não são permitidos caractéres númericos." );
			}

			DAO.alterar( hibernate, categoriaPO );
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

			CategoriaPO categoriaPO = null;

			if ( PO instanceof CategoriaPO ) {
				categoriaPO = (CategoriaPO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			DAO.excluir( hibernate, categoriaPO );
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

			CategoriaPO categoriaPO = null;

			if ( PO != null ) {
				if ( PO instanceof CategoriaPO ) {
					categoriaPO = (CategoriaPO) PO;
				} else {
					throw new BackendException( "Objeto PO não condiz com o contexto." );
				}
			}

			return DAO.filtrar( categoriaPO );

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
