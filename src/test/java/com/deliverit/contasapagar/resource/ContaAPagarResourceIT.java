/*
 * @(#) ContaAPagarResourceIT.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.resource;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.deliverit.contasapagar.dto.ContaAPagarDto;

/**
 * Classe de testes integrados para o recurso {@link ContaAPagarResource}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaAPagarResourceIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test_1_salvar_uma_nova_conta_a_pagar_que_nao_estiver_em_atraso_com_sucesso() throws JSONException {
		HttpEntity<ContaAPagarDto> request = new HttpEntity<>(new ContaAPagarDto(
				null, "Cartao Credito Master", BigDecimal.valueOf(499.89), null, 
				LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null));
		String expectedResponse = "{ \"id\": 1, \"nome\": \"Cartao Credito Master\", \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		ResponseEntity<String> actualResponse = this.restTemplate.postForEntity("/v1/contas-a-pagar", request, String.class);
		JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), false);
	}
	
	@Test
	public void test_2_salvar_uma_nova_conta_a_pagar_que_estiver_em_atraso_de_ate_3_dias_com_sucesso() throws JSONException {
		HttpEntity<ContaAPagarDto> request = new HttpEntity<>(new ContaAPagarDto(
				null, "Cartao Credito Visa", BigDecimal.valueOf(1000.0), null,
				LocalDate.of(2020, 06, 01), LocalDate.of(2020, 06, 03), null));
		String expectedResponse = "{ \"id\": 2, \"nome\": \"Cartao Credito Visa\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1022.0, \"dataVencimento\": \"2020-06-01\", \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 2 }";
		ResponseEntity<String> actualResponse = this.restTemplate.postForEntity("/v1/contas-a-pagar", request, String.class);
		JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), false);
	}
	
	@Test
	public void test_3_salvar_uma_nova_conta_a_pagar_que_estiver_em_atraso_superior_a_3_dias_com_sucesso() throws JSONException {
		HttpEntity<ContaAPagarDto> request = new HttpEntity<>(new ContaAPagarDto(
				null, "Cartao Credito Bradesco", BigDecimal.valueOf(1000.0), null,
				LocalDate.of(2020, 05, 29), LocalDate.of(2020, 06, 03), null));
		String expectedResponse = "{ \"id\": 3, \"nome\": \"Cartao Credito Bradesco\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1040.0, \"dataVencimento\": \"2020-05-29\", \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 5 }";
		ResponseEntity<String> actualResponse = this.restTemplate.postForEntity("/v1/contas-a-pagar", request, String.class);
		JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), false);
	}
	
	@Test
	public void test_4_salvar_uma_nova_conta_a_pagar_que_estiver_em_atraso_superior_a_5_dias_com_sucesso() throws JSONException {
		HttpEntity<ContaAPagarDto> request = new HttpEntity<>(new ContaAPagarDto(
				null, "Cartao Credito Nubank", BigDecimal.valueOf(1000.0), null,
				LocalDate.of(2020, 05, 26), LocalDate.of(2020, 06, 05), null));
		String expectedResponse = "{ \"id\": 4, \"nome\": \"Cartao Credito Nubank\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1080.0, \"dataVencimento\": \"2020-05-26\", \"dataPagamento\": \"2020-06-05\", \"quantidadeDiasAtraso\": 10 }";
		ResponseEntity<String> actualResponse = this.restTemplate.postForEntity("/v1/contas-a-pagar", request, String.class);
		JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), false);
	}
	
	@Test
	public void test_5_deve_retornar_a_lista_de_todas_as_contas_a_pagar() throws JSONException {
		String expectedResponse = "[ { \"nome\": \"Cartao Credito Master\", \"valorOriginal\": 499.89, \"dataPagamento\": \"2020-06-05\" }, { \"nome\": \"Cartao Credito Visa\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1022.0, \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 2 }, { \"nome\": \"Cartao Credito Bradesco\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1040.0, \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 5 }, { \"nome\": \"Cartao Credito Nubank\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1080.0, \"dataPagamento\": \"2020-06-05\", \"quantidadeDiasAtraso\": 10 } ]";
		String actualResponse = this.restTemplate.getForObject("/v1/contas-a-pagar", String.class);
		JSONAssert.assertEquals(expectedResponse, actualResponse, false);
	}
}
