/*
 * @(#) DiasEmAtraso.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.model;

import io.swagger.annotations.ApiModel;

/**
 * Classe enum que representa as regras de dias em atraso.
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@ApiModel(description = "Classe enum que representa as regras de dias em atraso.")
public enum DiasEmAtraso {

	/**
	 * Até 3 dias em atraso.
	 */
	ATE_3_DIAS,
	/**
	 * Superior a 3 dias em atraso.
	 */
	SUPERIOR_A_3_DIAS,
	/**
	 * Superior a 5 dias em atraso.
	 */
	SUPERIOR_A_5_DIAS;
}
