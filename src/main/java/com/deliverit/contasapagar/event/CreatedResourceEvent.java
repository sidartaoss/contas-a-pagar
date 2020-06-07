/*
 * @(#) CreatedResourceEvent.java      1.00    06/06/2020
 * Copyrights (c) 2020 Delivery IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * Classe de evento da aplicação para a criação de um novo recurso.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
public class CreatedResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long id;
	private Object objectId;

	public CreatedResourceEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public CreatedResourceEvent(Object source, HttpServletResponse response, Object objectId) {
		super(source);
		this.response = response;
		this.objectId = objectId;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}

	public Object getObjectId() {
		return objectId;
	}

}
