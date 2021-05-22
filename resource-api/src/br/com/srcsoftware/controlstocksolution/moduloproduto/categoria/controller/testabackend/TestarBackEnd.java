package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.testabackend;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller.CategoriaFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarBackEnd{

	public static void main( String[ ] args ) {
		try {
			/** Criando uma categoria */
			CategoriaPO po = new CategoriaPO();
			po.setNome( "Bebidas" );
			po.setDataHoraCadastrado( LocalDateTime.now() );

			/** Inserir */
			new CategoriaFACADE().inserir( po );

			/** Filtrando todos para ver se inserir */
			List encontrados = new CategoriaFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrar por id para alterar */
			CategoriaPO encontrado = (CategoriaPO) new CategoriaFACADE().filtrarPorId( "1" );
			System.out.println( encontrado.toString() );

			/** Alterar categoria encontrad */
			encontrado.setNome( "Bebidas Alteradas" );
			new CategoriaFACADE().alterar( encontrado );

			/** Filtrar por id para verificar alteração e excluir */
			encontrado = (CategoriaPO) new CategoriaFACADE().filtrarPorId( "1" );
			/** Excluindo */
			new CategoriaFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new CategoriaFACADE().filtrar( null );
			System.out.println( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}

}
