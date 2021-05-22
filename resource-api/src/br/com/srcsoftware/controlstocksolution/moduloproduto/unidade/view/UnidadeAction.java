package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.controller.UnidadeFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Messages;

public class UnidadeAction extends DispatchAction{

	private final UnidadeFACADE FACADE;

	public UnidadeAction(){
		FACADE = new UnidadeFACADE();
	}

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UnidadeForm unidadeForm = (UnidadeForm) form;

		unidadeForm.limparTela();

		return filtrar( mapping, form, request, response );
	}

	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirTela( mapping, form, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			UnidadeForm unidadeForm = (UnidadeForm) form;

			List< UnidadePO > encontrados = FACADE.filtrar( unidadeForm.getUnidade() );

			unidadeForm.getUnidades().clear();
			unidadeForm.getUnidades().addAll( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return mapping.findForward( "unidadeView" );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			UnidadeForm unidadeForm = (UnidadeForm) form;

			FACADE.inserir( unidadeForm.getUnidade() );

			unidadeForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return filtrar( mapping, form, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			UnidadeForm unidadeForm = (UnidadeForm) form;
			FACADE.alterar( unidadeForm.getUnidade() );

			unidadeForm.limparTela();

			this.addMessages( request, Messages.createMessages( "Sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return filtrar( mapping, form, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			UnidadeForm unidadeForm = (UnidadeForm) form;
			FACADE.excluir( unidadeForm.getUnidade() );

			unidadeForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return filtrar( mapping, form, request, response );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			UnidadeForm unidadeForm = (UnidadeForm) form;

			UnidadePO encontrada = (UnidadePO) FACADE.filtrarPorId( unidadeForm.getIdSelecionar() );

			unidadeForm.setUnidade( encontrada );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return mapping.findForward( "unidadeView" );
	}

}
