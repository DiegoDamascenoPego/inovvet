package com.inovvet.core.entity.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumTipoLoja {

	PETSHOP("PETSHOP"),
	CLINICA("CLINICA");

	private String value;

	public static EnumTipoLoja get(String value) {
		if (value == null) {
			return null;
		}

		for (EnumTipoLoja tipo : EnumTipoLoja.values()) {
			if (tipo.getValue().equals(value)) {
				return tipo;
			}
		}

		return null;
	}

	EnumTipoLoja(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
