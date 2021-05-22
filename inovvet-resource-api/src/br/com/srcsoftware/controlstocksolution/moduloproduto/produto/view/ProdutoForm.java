package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.controller.UnidadeFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class ProdutoForm extends ActionForm{
	private String idSelecionar;

	private ProdutoPO produto;

	private ArrayList< ProdutoPO > produtos;

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public ProdutoPO getProduto() {

		if ( produto == null ) {
			produto = new ProdutoPO();
		}

		return produto;
	}

	public void setProduto( ProdutoPO produtoPO ) {
		this.produto = produtoPO;
	}

	public ArrayList< ProdutoPO > getProdutos() {

		if ( produtos == null ) {
			produtos = new ArrayList< ProdutoPO >();
		}

		return produtos;
	}

	public void setProdutos( ArrayList< ProdutoPO > produtos ) {
		this.produtos = produtos;
	}

	public void limparTela() {
		setIdSelecionar( null );
		setProduto( null );
		getProdutos().clear();
	}

	public ArrayList< LabelValueBean > getComboCategorias() {

		ArrayList< LabelValueBean > options = new ArrayList< LabelValueBean >();

		try {
			CategoriaFACADE facade = new CategoriaFACADE();
			List< CategoriaPO > categorias = facade.filtrar( null );

			/** MONTANDO A LISTA DE LABELVALUE PARA ENVIAR AO JSP */

			for ( CategoriaPO categoriaCorrente : categorias ) {

				LabelValueBean labelValueBean = new LabelValueBean();
				labelValueBean.setLabel( categoriaCorrente.getNome() );
				labelValueBean.setValue( categoriaCorrente.getIdToString() );

				options.add( labelValueBean );

			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return options;
	}

	public ArrayList< LabelValueBean > getComboUnidades() {

		ArrayList< LabelValueBean > options = new ArrayList< LabelValueBean >();
		try {

			UnidadeFACADE facade = new UnidadeFACADE();

			List< UnidadePO > unidades = facade.filtrar( null );

			for ( UnidadePO unidadePO : unidades ) {
				LabelValueBean labelValueBean = new LabelValueBean();

				labelValueBean.setLabel( unidadePO.getSigla() + " - " + unidadePO.getNome() );
				labelValueBean.setValue( unidadePO.getIdToString() );

				options.add( labelValueBean );
			}

		} catch ( Exception e ) {
			// TODO: handle exception
		}
		return options;
	}

}
