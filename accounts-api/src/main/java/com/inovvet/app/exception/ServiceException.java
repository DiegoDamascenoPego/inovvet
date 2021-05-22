package com.inovvet.app.exception;

import org.springframework.http.HttpStatus;


public class ServiceException extends RuntimeException {

	 private static final long serialVersionUID = 1L;

		private EnumError errorException;

	    private HttpStatus httpStatus;

	    private String nome;

	    private String detalhe;


	    public ServiceException(EnumError error) {
	        super(error.getNome());
	        this.errorException = error;
	        this.httpStatus = error.getHttpStatus();
	        this.nome = error.getNome();
	        this.detalhe = error.getDetalhe();
	    }

	    public ServiceException(EnumError error, String detalhe) {
	        super(error.getNome());
	        this.errorException = error;
	        this.httpStatus = error.getHttpStatus();
	        this.nome = error.getNome();
	        this.detalhe = detalhe;
	    }

	    public ServiceException(EnumError error, Throwable e) {
	        super(error.getNome(), e);
	        this.errorException = error;
	        this.httpStatus = error.getHttpStatus();
	        this.nome = error.getNome();
	        this.detalhe = error.getDetalhe();
	    }


	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public String getDetalhe() {
	        return detalhe;
	    }

	    public EnumError getErrorException() {
	        return errorException;
	    }
}
