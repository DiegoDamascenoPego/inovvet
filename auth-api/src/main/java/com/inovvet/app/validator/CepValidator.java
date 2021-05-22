package com.inovvet.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.inovvet.app.util.FunctionUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepValidator implements ConstraintValidator<CepValidation, String> {

	private String value;

	@Override
	public void initialize(CepValidation constraintAnnotation) {
		this.value = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (FunctionUtil.isEmpty(value)) {
			return true;
		}

		return FunctionUtil.validarCEP(value);
	}

}
