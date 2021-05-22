package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumModulo {
	
	CADASTRO("CADASTRO"),
	FINANCEIRO("FINANCEIRO"),
	FISCAL("FISCAL");
	
	public static EnumModulo get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumModulo tipo : EnumModulo.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumModulo(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }


}
