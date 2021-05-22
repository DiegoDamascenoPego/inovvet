package com.inovvet.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ArgumentNotValidExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Errors argumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private Errors processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		Errors error = new Errors(HttpStatus.BAD_REQUEST.value(), "Campos Obrigatório não informado");
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
		}
		return error;
	}

	@Getter
	@Setter
	static class Errors {
		private final int status;
		private final String mensagem;
		private List<Error> errors = new ArrayList<>();

		Errors(int status, String message) {
			this.status = status;
			this.mensagem = message;
		}

		public void addFieldError(String objectName, String campo, String mensagem) {
			this.errors.add(new Error(campo, mensagem));
		}

	}

	@Getter
	@Setter
	static class Error {
		private String campo;
		private String mensagem;

		public Error(String campo, String mensagem) {
			this.campo = campo;
			this.mensagem = mensagem;
		}
	}

}
