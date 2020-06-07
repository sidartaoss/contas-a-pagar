/*
 * @(#) ContaAPagarResourceTest.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.resource;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.deliverit.contasapagar.model.ContaAPagar;
import com.deliverit.contasapagar.service.ContaAPagarService;

/**
 * Classe de testes unitários para o recurso {@link ContaAPagarResource}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ContaAPagarResource.class)
public class ContaAPagarResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContaAPagarService contaAPagarService;
	
	@Test
	public void test_deve_retornar_erro_400_bad_request_quando_nome_nao_for_informado() throws Exception {
		// Scenario
		String requestBody = "{ \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestBody)
				.contentType(APPLICATION_JSON);
		
		// Action & Verification
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest())
			.andExpect(content().json("{\"statusCode\":400, \"errors\":[{\"code\":\"contas-a-pagar-1\"}]}"))
			.andReturn();
		Optional<MethodArgumentNotValidException> methodArgumentNotValidException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(notNullValue())));
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(instanceOf(MethodArgumentNotValidException.class))));
	}
	
	@Test
	public void test_deve_retornar_erro_400_bad_request_quando_tamanho_do_nome_for_menor_que_3_caracteres() throws Exception {
		// Scenario
		String requestBody = "{ \"nome\": \"Ka\", \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestBody)
				.contentType(APPLICATION_JSON);
		
		// Action & Verification
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest())
			.andExpect(content().json("{\"statusCode\":400, \"errors\":[{\"code\":\"contas-a-pagar-2\"}]}"))
			.andReturn();
		Optional<MethodArgumentNotValidException> methodArgumentNotValidException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(notNullValue())));
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(instanceOf(MethodArgumentNotValidException.class))));
	}
	
	@Test
	public void test_deve_retornar_erro_400_bad_request_quando_valor_original_nao_for_informado() throws Exception {
		// Scenario
		String requestBody = "{ \"nome\": \"Cartao Credito\", \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestBody)
				.contentType(APPLICATION_JSON);
		
		// Action & Verification
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest())
			.andExpect(content().json("{\"statusCode\":400, \"errors\":[{\"code\":\"contas-a-pagar-3\"}]}"))
			.andReturn();
		Optional<MethodArgumentNotValidException> methodArgumentNotValidException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(notNullValue())));
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(instanceOf(MethodArgumentNotValidException.class))));
	}
	
	@Test
	public void test_deve_retornar_erro_400_bad_request_quando_data_vencimento_nao_for_informado() throws Exception {
		// Scenario
		String requestBody = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 499.89, \"dataPagamento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestBody)
				.contentType(APPLICATION_JSON);
		
		// Action & Verification
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest())
			.andExpect(content().json("{\"statusCode\":400, \"errors\":[{\"code\":\"contas-a-pagar-4\"}]}"))
			.andReturn();
		Optional<MethodArgumentNotValidException> methodArgumentNotValidException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(notNullValue())));
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(instanceOf(MethodArgumentNotValidException.class))));
	}
	
	@Test
	public void test_deve_retornar_erro_400_bad_request_quando_data_pagamento_nao_for_informado() throws Exception {
		// Scenario
		String requestBody = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestBody)
				.contentType(APPLICATION_JSON);
		
		// Action & Verification
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest())
			.andExpect(content().json("{\"statusCode\":400, \"errors\":[{\"code\":\"contas-a-pagar-5\"}]}"))
			.andReturn();
		Optional<MethodArgumentNotValidException> methodArgumentNotValidException = Optional.ofNullable((MethodArgumentNotValidException) result.getResolvedException());
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(notNullValue())));
		methodArgumentNotValidException.ifPresent((mnve) -> assertThat(mnve, is(instanceOf(MethodArgumentNotValidException.class))));
	}
	
	@Test
	public void test_deve_retornar_201_created_e_cadastrar_uma_nova_conta_a_pagar_que_nao_estiver_em_atraso_com_sucesso() throws Exception {
		// Scenario
		String requestJson = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		String responseJson = "{ \"id\": 1, \"nome\": \"Cartao Credito\", \"valorOriginal\": 499.89, \"dataVencimento\": \"2020-06-05\", \"dataPagamento\": \"2020-06-05\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestJson)
				.contentType(APPLICATION_JSON);
		
		when(contaAPagarService.salvar(any(ContaAPagar.class), any(HttpServletResponse.class))).thenReturn(
				new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(499.89), null,
						LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null));
		
		// Action & Verification
		mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson))
			.andReturn();
		
	}
	
	@Test
	public void test_deve_retornar_201_created_e_cadastrar_uma_nova_conta_a_pagar_que_estiver_em_atraso_de_ate_3_dias_com_sucesso() throws Exception {
		// Scenario
		String requestJson = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"dataVencimento\": \"2020-06-01\", \"dataPagamento\": \"2020-06-03\" }";
		String responseJson = "{ \"id\": 1, \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1022.0, \"dataVencimento\": \"2020-06-01\", \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 2 }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestJson)
				.contentType(APPLICATION_JSON);
		
		when(contaAPagarService.salvar(any(ContaAPagar.class), any(HttpServletResponse.class))).thenReturn(
				new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1022.0),
						LocalDate.of(2020, 06, 01), LocalDate.of(2020, 06, 03), 2L));
		
		// Action & Verification
		mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson))
			.andReturn();
		
	}
	
	@Test
	public void test_deve_retornar_201_created_e_cadastrar_uma_nova_conta_a_pagar_que_estiver_em_atraso_superior_a_3_dias_com_sucesso() throws Exception {
		// Scenario
		String requestJson = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"dataVencimento\": \"2020-05-29\", \"dataPagamento\": \"2020-06-03\" }";
		String responseJson = "{ \"id\": 1, \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1040.0, \"dataVencimento\": \"2020-05-29\", \"dataPagamento\": \"2020-06-03\", \"quantidadeDiasAtraso\": 5 }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestJson)
				.contentType(APPLICATION_JSON);
		
		when(contaAPagarService.salvar(any(ContaAPagar.class), any(HttpServletResponse.class))).thenReturn(
				new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1040.0),
						LocalDate.of(2020, 05, 29), LocalDate.of(2020, 06, 03), 5L));
		
		// Action & Verification
		mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson))
			.andReturn();
		
	}
	
	@Test
	public void test_deve_retornar_201_created_e_cadastrar_uma_nova_conta_a_pagar_que_estiver_em_atraso_superior_a_5_dias_com_sucesso() throws Exception {
		// Scenario
		String requestJson = "{ \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"dataVencimento\": \"2020-05-26\", \"dataPagamento\": \"2020-06-05\" }";
		String responseJson = "{ \"id\": 1, \"nome\": \"Cartao Credito\", \"valorOriginal\": 1000.0, \"valorCorrigido\": 1080.0, \"dataVencimento\": \"2020-05-26\", \"dataPagamento\": \"2020-06-05\", \"quantidadeDiasAtraso\": 10 }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.content(requestJson)
				.contentType(APPLICATION_JSON);
		
		when(contaAPagarService.salvar(any(ContaAPagar.class), any(HttpServletResponse.class))).thenReturn(
				new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1080.0),
						LocalDate.of(2020, 05, 26), LocalDate.of(2020, 06, 05), 10L));
		
		// Action & Verification
		mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andExpect(content().json(responseJson))
			.andReturn();
		
	}
	
	@Test
	public void test_deve_retornar_200_ok_e_listar_todas_as_contas_a_pagar() throws Exception {
		// Scenario
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1/contas-a-pagar")
				.accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON);
		
		when(contaAPagarService.buscar()).thenReturn(
				Arrays.asList(new ContaAPagar(1L, "Cartao Credito Master"),
						new ContaAPagar(2L, "Cartao Credito Visa"),
						new ContaAPagar(3L, "Cartao Credito Bradesco"),
						new ContaAPagar(4L, "Cartao Credito Nubank")
						));
		
		// Action & Verification
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().json("[{ nome: \"Cartao Credito Master\" }, { nome: \"Cartao Credito Visa\" }, { nome: \"Cartao Credito Bradesco\" }, { nome: \"Cartao Credito Nubank\" }]"))
			.andReturn();
		
	}
}
