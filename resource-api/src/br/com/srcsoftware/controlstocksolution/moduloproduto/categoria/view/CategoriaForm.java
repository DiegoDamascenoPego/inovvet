package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.view;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;

public final class CategoriaForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	private String idSelecionar;

	private CategoriaPO categoria;

	private ArrayList< CategoriaPO > categorias;

	public CategoriaPO getCategoria() {
		if ( categoria == null ) {
			categoria = new CategoriaPO();
		}
		return categoria;
	}

	public void setCategoria( CategoriaPO categoria ) {
		this.categoria = categoria;
	}

	public ArrayList< CategoriaPO > getCategorias() {

		if ( categorias == null ) {
			categorias = new ArrayList< CategoriaPO >();
		}

		return categorias;
	}

	public void setCategorias( ArrayList< CategoriaPO > categorias ) {
		this.categorias = categorias;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public void limparTela() {
		setIdSelecionar( null );
		setCategoria( null );
		getCategorias().clear();
	}

}
