/*
 * @(#) ApiErrorConfig.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Classe de configuração responsável por mapear o arquivo
 * {@code api_errors.properties} no path da aplicação.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Configuration
public class ApiErrorConfig {

	/**
	 * Método responsável por mapear o arquivo {@code api_errors.properties} no path
	 * da aplicação.
	 * 
	 * @return um objeto {@link MessageSource}.
	 */
	@Bean
	public MessageSource apiErrorMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/api_errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
