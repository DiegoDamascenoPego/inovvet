package com.inovvet.app.exception;

import org.springframework.http.HttpStatus;

public enum EnumError {

	 FALHA_SALVAR_REGISTRO(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha ao salvar as informações.",
	            "Devido à um erro inesperado, não foi possível realizar a operação"),
	 FALHA_INESPERADA(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha inesperada.",
	            "Devido à um erro inesperado, não foi possível realizar a operação"),
	 FALHA_CONVERTER_REGISTRO(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha ao converter as informações.",
	            "Devido à um erro inesperado, não foi possível realizar a operação"),
	 PARAMETROS_INVALIDOS(
	            HttpStatus.BAD_REQUEST,
	            "Erro",
	            "Informações inválidas",
	            "O corpo e/ou parametros da requisição inválidos"),
	 FALHA_API(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha inesperada.",
			 	"Devido à um erro inesperado, não foi possível realizar a operação"),
	CONTA_INVALIDA(
	            HttpStatus.CONFLICT,
	            "Erro",
	            "Conta Inválida",
	            "CPF informado já registrado para uma conta");
	 
	 EnumError(HttpStatus httpStatus, String tipo, String nome, String detalhe) {
	        this.httpStatus = httpStatus;
	        this.tipo = tipo;
	        this.nome = nome;
	        this.detalhe = detalhe;
	    }


	    private HttpStatus httpStatus;

	    private String nome;

	    private String detalhe;

	    private String tipo;


	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public String getDetalhe() {
	        return detalhe;
	    }

	    public String getTipo() {
	        return tipo;
	    }

}
