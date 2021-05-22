package com.inovvet.app.config;

import com.inovvet.core.entity.conta.dto.ContaDTO;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.usuario.Usuario;

public final class Contexto {

	private static final ThreadLocal<ContaDTO> CONTA = new ThreadLocal<>();

	private static final ThreadLocal<Loja> LOJA = new ThreadLocal<>();

	private static ThreadLocal<String> CONTASCHEMA = new ThreadLocal<>();
	
	private static final ThreadLocal<Usuario> USUARIO = new ThreadLocal<>();

	public static void SetConta(ContaDTO conta) {
		CONTA.set(conta);
	}

	public static ContaDTO getConta() {
		return CONTA.get();
	}

	public static void SetLoja(Loja loja) {
		LOJA.set(loja);
	}

	public static Loja getLoja() {
		return LOJA.get();
	}

	public static String getContaSchema() {
		return CONTASCHEMA.get();
	}

	public static void setContaSchema(String schema) {
		CONTASCHEMA.set(schema);
	}
	
	public static void setUsuario(Usuario usuario) {
		USUARIO.set(usuario);
	}
	
	public static Usuario getUsuario() {
		return USUARIO.get();
	}

	public static void clear() {
		CONTA.remove();
		CONTASCHEMA.remove();
		LOJA.remove();
		USUARIO.remove();
	}
}
