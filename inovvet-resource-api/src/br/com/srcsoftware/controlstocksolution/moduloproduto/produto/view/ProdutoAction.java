package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.ProdutoFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Messages;

public class ProdutoAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ProdutoForm produtoForm = (ProdutoForm) form;
		produtoForm.limparTela();

		return filtrar( mapping, produtoForm, request, response );

	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ProdutoForm produtoForm = (ProdutoForm) form;

			List< ProdutoPO > encontrados;

			ProdutoFACADE facade = new ProdutoFACADE();

			encontrados = facade.filtrar( produtoForm.getProduto() );

			produtoForm.getProdutos().clear();

			produtoForm.getProdutos().addAll( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return mapping.findForward( "produtoView" );

	}

	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirTela( mapping, form, request, response );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			ProdutoForm produtoForm = (ProdutoForm) form;
			ProdutoFACADE facade = new ProdutoFACADE();

			facade.inserir( produtoForm.getProduto() );
			produtoForm.limparTela();

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

			ProdutoForm produtoForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();

			facade.alterar( produtoForm.getProduto() );

			produtoForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessages( "erro", "Erro Desconhecido" + e.getMessage() ) );
		}

		return filtrar( mapping, form, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {

			ProdutoForm produtoForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();

			facade.excluir( produtoForm.getProduto() );

			produtoForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessages( "erro", "Erro Desconhecido" + e.getMessage() ) );
		}

		return filtrar( mapping, form, request, response );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			ProdutoForm produtoForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();

			ProdutoPO encontrado = (ProdutoPO) facade.filtrarPorId( produtoForm.getIdSelecionar() );

			produtoForm.setProduto( encontrado );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessages( "erro", "Erro Desconhecido" + e.getMessage() ) );
		}

		return mapping.findForward( "produtoView" );
	}
}
