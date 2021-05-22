package com.inovvet.core.enumerator;

import java.time.DayOfWeek;

public enum EnumDiaSemana {

	DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO;

	public static EnumDiaSemana get(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
		case MONDAY:
			return SEGUNDA;
		case TUESDAY:
			return TERCA;
		case WEDNESDAY:
			return QUARTA;
		case THURSDAY:
			return QUINTA;
		case FRIDAY:
			return SEXTA;
		default:
			return DOMINGO;
		}
	}

}
