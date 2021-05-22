package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.testabackend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.ProdutoFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarBackEnd{
	public static void main( String[ ] args ) {
		try {

			/** Criando uma Produto */
			ProdutoPO po = new ProdutoPO();
			po.setNome( "Kaizer" );
			po.setPreco( BigDecimal.valueOf( 2.50 ) );
			//			po.setUnidadeMedida( "LT" );
			po.setDataHoraCadastrado( LocalDateTime.now() );
			po.setCategoria( criarCategoria() );

			/** Inserir */
			new ProdutoFACADE().inserir( po );

			/** Filtrando todos para ver se inserir */
			List encontrados = new ProdutoFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrar por id para alterar */
			ProdutoPO encontrado = (ProdutoPO) new ProdutoFACADE().filtrarPorId( "1" );
			System.out.println( encontrado.toString() );

			/** Alterar categoria encontrad */
			encontrado.setNome( "skol" );
			encontrado.setPreco( BigDecimal.valueOf( 3.50 ) );
			//			encontrado.setUnidadeMedida( "GA" );
			new ProdutoFACADE().alterar( encontrado );

			/** Filtrar por id para verificar alteração e excluir */
			encontrado = (ProdutoPO) new ProdutoFACADE().filtrarPorId( "1" );
			/** Excluindo */
			new ProdutoFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new ProdutoFACADE().filtrar( null );
			System.out.println( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}

	private static CategoriaPO criarCategoria() throws BackendException {

		CategoriaPO po = new CategoriaPO();
		po.setNome( "Bebidas" );
		po.setDataHoraCadastrado( LocalDateTime.now() );

		new CategoriaFACADE().inserir( po );

		return po;
	}
}
