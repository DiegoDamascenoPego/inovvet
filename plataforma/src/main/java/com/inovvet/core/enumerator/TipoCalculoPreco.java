package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCalculoPreco {

	CUSTO("CUSTO"),
	CUSTOMEDIO("CUSTOMEDIO");
	
	public static TipoCalculoPreco get(String value) {
        if (value == null) {
            return null;
        }

        for (TipoCalculoPreco tipo : TipoCalculoPreco.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	TipoCalculoPreco(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
