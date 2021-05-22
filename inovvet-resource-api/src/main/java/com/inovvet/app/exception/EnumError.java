package com.inovvet.app.exception;

import org.springframework.http.HttpStatus;

public enum EnumError {

	 FALHA_SALVAR_REGISTRO(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha ao salvar as informações.",
	            "Devido à um erro inesperado, não foi possível realizar a operação"),
	 FALHA_REMOVER_REGISTRO(
			    HttpStatus.INTERNAL_SERVER_ERROR,
	            "Erro",
	            "Falha ao remover as informações.",
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
	            "Alerta",
	            "Informações inválidas",
	            "O corpo e/ou parametros da requisição inválidos"),
	 REGISTRO_NAO_ECONTRADO(
	            HttpStatus.BAD_REQUEST,
	            "Erro",
	            "Registro não encontrado",
	            "O Registro informado não foi encontrado"),
	 REGISTRO_INVALIDO(
	            HttpStatus.BAD_REQUEST,
	            "Erro",
	            "Informações inválidas",
	            "O Registro informado não foi Encontrado."),
	 REGISTRO_DUPLICADO(
	            HttpStatus.CONFLICT,
	            "Erro",
	            "Informações inválidas",
	            "O Registro informado já está registrado."),
	CONTA_INVALIDA(
	            HttpStatus.CONFLICT,
	            "Erro",
	            "Conta Inválida",
	            "CPF informado já registrado para uma conta"),
	DATA_INDISPONIVEL(
            HttpStatus.CONFLICT,
            "Alerta",
            "Informações inválidas",
            "A data Informada Não está mais Disponível"),
	ALTERACAO_NAO_PERMITIDA(
            HttpStatus.CONFLICT,
            "Alerta",
            "Registro não pode ser Modificado",
            "O Registro informado não pode ser modificado");
	 
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
