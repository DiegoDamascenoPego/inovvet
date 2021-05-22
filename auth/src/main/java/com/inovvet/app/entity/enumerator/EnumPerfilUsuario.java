package com.inovvet.app.entity.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;


public enum EnumPerfilUsuario {
	ADMINISTRADOR("Administrador"), VENDEDOR("Vendedor");

	private String value;

	public static EnumPerfilUsuario get(String value) {
		if (value == null) {
			return null;
		}

		for (EnumPerfilUsuario tipo : EnumPerfilUsuario.values()) {
			if (tipo.getValue().equals(value)) {
				return tipo;
			}
		}

		return null;
	}

	EnumPerfilUsuario(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
	

}
