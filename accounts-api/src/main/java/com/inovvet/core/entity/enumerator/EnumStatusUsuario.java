package com.inovvet.core.entity.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumStatusUsuario {
	INATIVO("Inativo"),
	ATIVO("Ativo"),
	CREDENCIAISEXPIRADA("Credencial Expirada"),
	BLOQUEADA("Bloqueada");

private String value;

	public static EnumStatusUsuario get(String value) {
		if (value == null) {
			return null;
		}

		for (EnumStatusUsuario tipo : EnumStatusUsuario.values()) {
			if (tipo.getValue().equals(value)) {
				return tipo;
			}
		}

		return null;
	}

	EnumStatusUsuario(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

}
