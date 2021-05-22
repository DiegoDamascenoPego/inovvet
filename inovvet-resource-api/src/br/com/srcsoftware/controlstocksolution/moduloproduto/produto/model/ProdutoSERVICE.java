package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.dao.ProdutoDAO;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;

/**
 * 
 * Classe que representa a camada de negócio da Produto
 * nesta classe são implementadas todas as regras de negócio necessárias para garantir a consistência dos dados contidos no PO e ou/
 * garantir o correto funcionamento do processo solicitado
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 24 de jul de 2018 22:16:02
 * @version 1.0
 */
public final class ProdutoSERVICE implements Crud{

	/** Garante a aplicação da associação entre o SERVICE e o DAO */
	/**
	 * Toda constante deve possuir um valor
	 * os unicos lugares possíveis de inicialização de uma constante são:
	 * - No ato da declaração
	 * final ProdutoDAO DAO = new ProdutoDAO();
	 * - Na primeira linha do construtor
	 * --- public ProdutoSERVICE(){
	 * ---------DAO = new ProdutoDAO();
	 * 
	 * - Num BLOCO ESTATICO
	 * -----------STATIC {
	 * ------------DAO = new ProdutoDAO();
	 * ---}
	 */
	private final ProdutoDAO DAO;

	public ProdutoSERVICE(){
		DAO = new ProdutoDAO();
	}

	@Override
	public void inserir( final AbsctractPO po ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransicao();
			if ( po == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			ProdutoPO produtoPo = null;

			if ( po instanceof ProdutoPO ) {
				produtoPo = (ProdutoPO) po;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			DAO.inserir( hibernate, produtoPo );
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

			ProdutoPO produtoPo = null;

			if ( PO instanceof ProdutoPO ) {
				produtoPo = (ProdutoPO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			DAO.alterar( hibernate, produtoPo );
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

			ProdutoPO produtoPo = null;

			if ( PO instanceof ProdutoPO ) {
				produtoPo = (ProdutoPO) PO;
			} else {
				throw new BackendException( "Objeto PO não condiz com o contexto." );
			}

			DAO.excluir( hibernate, produtoPo );
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

			ProdutoPO produtoPo = null;

			if ( PO != null ) {
				if ( PO instanceof ProdutoPO ) {
					produtoPo = (ProdutoPO) PO;
				} else {
					throw new BackendException( "Objeto PO não condiz com o contexto." );
				}
			}

			return DAO.filtrar( produtoPo );

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecimento ao Excluir", e );
		}

	}

	@Override
	public AbsctractPO filtrarPorId( final String ID ) throws BackendException {
		try {
			if ( ID == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro." );
			}

			return DAO.filtrarPorId( Long.valueOf( ID ) );

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecimento ao Excluir", e );
		}
	}

}
