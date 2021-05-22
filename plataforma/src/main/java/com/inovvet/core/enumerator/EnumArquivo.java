package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumArquivo {
	IMAGEM("IMAGEM"),
	BOLETO("BOLETO");
	
	public static EnumArquivo get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumArquivo tipo : EnumArquivo.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumArquivo(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }


}
