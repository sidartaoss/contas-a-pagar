/*
 * @(#) JsonConfig.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.config;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe utilitária de conversão para o formato Json.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
public class JsonConfig {

	private JsonConfig() {
	}

	/**
	 * Método responsável por converter o parâmetro {@link Object} para o formato
	 * Json.
	 * 
	 * @param obj o objeto a ser convertido no formato Json.
	 * @return o objeto convertido no formato Json.
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
