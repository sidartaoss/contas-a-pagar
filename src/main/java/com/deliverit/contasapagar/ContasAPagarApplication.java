/*
 * @(#) ContasAPagarApplication.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Classe principal da aplicação.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@EntityScan("com.deliverit.contasapagar.model")
@SpringBootApplication
public class ContasAPagarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasAPagarApplication.class, args);
	}

}
