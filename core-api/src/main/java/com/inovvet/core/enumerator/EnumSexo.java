package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumSexo {

	MASCULINO("MASCULINO"),
	FEMININO("FEMININO");
	
	public static EnumSexo get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumSexo tipo : EnumSexo.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumSexo(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }
	
}
