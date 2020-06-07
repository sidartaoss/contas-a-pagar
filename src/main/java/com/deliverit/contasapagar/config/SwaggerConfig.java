/*
 * @(#) SwaggerConfig.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.config;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração para documentação da API com Swagger.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Deliver IT", "http://deliverit.com.br/",
			"contato@deliverit.com.br");

	@SuppressWarnings("rawtypes")
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("API REST Gerenciamento de Contas A Pagar",
			"API REST para gerenciamento de contas a pagar", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

	/**
	 * Método responsável pela configuração da documentação da API com Swagger.
	 * 
	 * @return o objeto {@link Docket}.
	 */
	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}
}