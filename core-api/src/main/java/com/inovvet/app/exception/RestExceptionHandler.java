package com.inovvet.app.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handler(ServiceException exception) {

		JsonObject response = new JsonObject();
		response.addProperty("tipo", exception.getErrorException().getTipo());
		response.addProperty("titulo", exception.getErrorException().getNome());
		response.addProperty("descricao", exception.getDetalhe());

		return new ResponseEntity<>(new Gson().toJson(response), exception.getHttpStatus());
	}

	
}
