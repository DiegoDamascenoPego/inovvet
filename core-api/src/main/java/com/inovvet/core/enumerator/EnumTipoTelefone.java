package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumTipoTelefone {
	
	CELULAR("CELULAR"),
	RESIDENCIAL("RESIDENCIAL"),
	COMERCIAL("COMERCIAL");
	
	public static EnumTipoTelefone get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumTipoTelefone tipo : EnumTipoTelefone.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumTipoTelefone(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }
		
}
