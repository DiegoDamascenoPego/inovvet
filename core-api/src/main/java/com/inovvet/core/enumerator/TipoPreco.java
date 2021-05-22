package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPreco {
	VAREJO("VAREJO"),
	ATACADO("ATACADO");
	
	public static TipoPreco get(String value) {
        if (value == null) {
            return null;
        }

        for (TipoPreco tipo : TipoPreco.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	TipoPreco(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
