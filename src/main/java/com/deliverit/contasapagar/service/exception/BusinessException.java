/*
 * @(#) BusinessException.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.service.exception;

import org.springframework.http.HttpStatus;

/**
 * Classe de exceção de negócio para a camada de serviços.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;
	private final HttpStatus status;

	public BusinessException(String code, HttpStatus status) {
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
