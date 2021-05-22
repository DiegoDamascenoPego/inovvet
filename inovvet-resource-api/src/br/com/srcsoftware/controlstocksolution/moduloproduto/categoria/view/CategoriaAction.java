package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.view;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Messages;

/**
 * 
 * Classe que representa
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 10 de ago de 2018 21:58:45
 * @version 1.0
 */
public class CategoriaAction extends DispatchAction{

	/**
	 * Método responsável por abrir a tela de cadastro de aluno.
	 * 
	 * Todo médodo da ACTION que será acessivel da tela terá
	 * o mesmo RETORNO(ActionForward), mesmos PARAMETROS(ActionMapping mapping,
	 * ActionForm form, HttpServletRequest request,
	 * HttpServletResponse response) e mesmo RETORNO(mapping.findForward( "nome_do_forward_aqui" ))
	 *
	 * @param ActionMapping mapping - Variavél que possibilita o ACESSO
	 *        á TAG <action-mappings> do struts-config.xml
	 * @param ActionForm form - Variavel que contem todos os dados vindos da tela setados em seus ATRIBUTOS
	 * @param HttpServletRequest request - Permite recuperar dados da tela sem que seja por intermedio do Struts
	 * @param HttpServletResponse response - Permite manipular a tela sem que seja por intermedio do Struts
	 * @return ActionForward - Forward referente a tela que deverá ser aberta apos a execução do Metodo. O valor passado como
	 *         parametro esta definido na propriedade NAME da TAG <forward> da TAG <action-mappings> do arquivo struts-config.xml.
	 * 
	 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
	 * @since 10 de ago de 2018 22:00:38
	 * @version 1.0
	 */
	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		CategoriaForm categoriaForm = (CategoriaForm) form;
		categoriaForm.limparTela();
		categoriaForm.getCategoria().setDataHoraCadastrado( LocalDateTime.now() );

		return filtrar( mapping, categoriaForm, request, response );

	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			CategoriaForm categoriaForm = (CategoriaForm) form;

			List< CategoriaPO > encontrados;

			CategoriaFACADE facade = new CategoriaFACADE();

			encontrados = facade.filtrar( categoriaForm.getCategoria() );

			categoriaForm.getCategorias().clear();

			categoriaForm.getCategorias().addAll( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return mapping.findForward( "categoriaView" );
	}

	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirTela( mapping, form, request, response );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			CategoriaForm categoriaForm = (CategoriaForm) form;
			CategoriaFACADE facade = new CategoriaFACADE();

			facade.inserir( categoriaForm.getCategoria() );
			categoriaForm.limparTela();

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
			CategoriaForm categoriaForm = (CategoriaForm) form;
			CategoriaFACADE facade = new CategoriaFACADE();

			facade.alterar( categoriaForm.getCategoria() );
			categoriaForm.limparTela();

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

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			CategoriaForm categoriaForm = (CategoriaForm) form;
			CategoriaFACADE facade = new CategoriaFACADE();

			facade.excluir( categoriaForm.getCategoria() );
			categoriaForm.limparTela();

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
			CategoriaForm categoriaForm = (CategoriaForm) form;

			CategoriaPO encontrado;

			CategoriaFACADE facade = new CategoriaFACADE();

			encontrado = (CategoriaPO) facade.filtrarPorId( categoriaForm.getIdSelecionar() );

			categoriaForm.setCategoria( encontrado );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", " erro desconhecido " + e.getMessage() ) );
		}

		return mapping.findForward( "categoriaView" );
	}

}
