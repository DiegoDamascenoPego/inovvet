package com.inovvet.core.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumRegimeEmpresa {
	MEI("Micro Empreendeor Individual"),
	SIMPLES("Simples Nacional"),
	LUCROPRESUMIDO("Lucro Presumido"),
	LUCROREAL("Lucro Real");
	
	public static EnumRegimeEmpresa get(String value) {
        if (value == null) {
            return null;
        }

        for (EnumRegimeEmpresa tipo : EnumRegimeEmpresa.values()) {
            if (tipo.getValue().equals(value)) {
                return tipo;
            }
        }

        return null;
    }


	EnumRegimeEmpresa(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
