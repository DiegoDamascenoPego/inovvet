package com.inovvet.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inovvet.app.util.ValidMessages;

import lombok.Getter;
import lombok.Setter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ArgumentNotValidExceptionHandler {

	private ResponseEntity<Object> prepararRetorno(String error, HttpStatus status) {
		JsonObject response = new JsonObject();
		response.addProperty("tipo", "Erro");
		response.addProperty("titulo", "Informações inválidas");
		response.addProperty("descricao", "Parâmetros inválidos");
		response.addProperty("detalhe", error);

		return new ResponseEntity<>(new Gson().toJson(response), status);

	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
			HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Object> handler(Exception exception, WebRequest request) {
		if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;

			return prepararRetorno(ValidMessages.retornaMensagemErro(ex.getBindingResult()), HttpStatus.BAD_REQUEST);

		}

		if (exception instanceof HttpMessageNotReadableException) {
			HttpMessageNotReadableException ex = (HttpMessageNotReadableException) exception;

			return prepararRetorno(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

		if (exception instanceof HttpRequestMethodNotSupportedException) {
			HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) exception;
			JsonObject response = new JsonObject();
			response.addProperty("tipo", "Erro");
			response.addProperty("titulo", "Recurso não disponível");
			response.addProperty("descricao", "Parâmetros inválidos");
			response.addProperty("detalhe", ex.getMessage());

			return prepararRetorno(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
		}
		
		return null;

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
