package com.inovvet.core.entity.animal;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumTipoPelo {
	CURTO("CURTO"),
	LONGO("LONGO");
	
	public static EnumTipoPelo get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumTipoPelo tipo : EnumTipoPelo.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumTipoPelo(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
