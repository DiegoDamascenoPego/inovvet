package com.inovvet.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.inovvet.app.util.FunctionUtil;

public class DocumentoValidator implements ConstraintValidator<DocumentoValidation, String> {

	private String value;

	@Override
	public void initialize(DocumentoValidation constraintAnnotation) {
		this.value = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.isEmpty() || FunctionUtil.isCpf(value) || FunctionUtil.isCnpj(value);
	}


}
