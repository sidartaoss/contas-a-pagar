/*
 * @(#) ContaAPagarServiceTest.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import com.deliverit.contasapagar.model.ContaAPagar;
import com.deliverit.contasapagar.model.DiasEmAtraso;
import com.deliverit.contasapagar.model.RegraCalculoContaEmAtraso;
import com.deliverit.contasapagar.repository.ContaAPagarRepository;
import com.deliverit.contasapagar.service.exception.BusinessException;

/**
 * Classe de testes unitários para o serviço {@link ContaAPagarService}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@RunWith(MockitoJUnitRunner.class)
public class ContaAPagarServiceTest {

	@InjectMocks
	private ContaAPagarService contaAPagarService;

	@Mock
	private ContaAPagarRepository contaAPagarRepository;
	
	@Mock
	private RegraCalculoContaEmAtrasoService regraCalculoContaEmAtrasoService;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Mock
	private ApplicationEventPublisher publisher;

	@Test
	public void test_deve_disparar_excecao_quando_salvar_e_atributo_nome_nao_for_informado() {
		// Scenario
		ContaAPagar contaAPagar = new ContaAPagar(1L, null, BigDecimal.valueOf(499.89), null,
				LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Verification
		exception.expect(BusinessException.class);

		// Action
		this.contaAPagarService.salvar(contaAPagar, response);
	}
	
	@Test
	public void test_deve_disparar_excecao_quando_salvar_e_atributo_valor_original_nao_for_informado() {
		// Scenario
		ContaAPagar contaAPagar = new ContaAPagar(1L, "Cartao Credito", null, null,
				LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Verification
		exception.expect(BusinessException.class);

		// Action
		this.contaAPagarService.salvar(contaAPagar, response);
	}
	
	@Test
	public void test_deve_disparar_excecao_quando_salvar_e_atributo_data_vencimento_nao_for_informado() {
		// Scenario
		ContaAPagar contaAPagar = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(999.79), null,
				null, LocalDate.of(2020, 06, 05), null);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Verification
		exception.expect(BusinessException.class);

		// Action
		this.contaAPagarService.salvar(contaAPagar, response);
	}
	
	@Test
	public void test_deve_disparar_excecao_quando_salvar_e_atributo_data_pagamento_nao_for_informado() {
		// Scenario
		ContaAPagar contaAPagar = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(999.79), null,
				LocalDate.of(2020, 06, 05), null, null);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Verification
		exception.expect(BusinessException.class);

		// Action
		this.contaAPagarService.salvar(contaAPagar, response);
	}
	
	@Test
	public void test_deve_salvar_conta_a_pagar_que_nao_estiver_em_atraso_com_sucesso() {
		// Scenario
		ContaAPagar expected = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(999.79), null,
				LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null);
		
		when(this.contaAPagarRepository.save(Mockito.any(ContaAPagar.class))).thenReturn(expected);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Action
		ContaAPagar actual = this.contaAPagarService.salvar(expected, response);
		
		// Verification
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNome(), actual.getNome());
		assertEquals(expected.getValorOriginal(), actual.getValorOriginal());
		assertEquals(expected.getDataPagamento(), actual.getDataPagamento());
	}
	
	@Test
	public void test_deve_salvar_conta_a_pagar_que_estiver_em_atraso_de_ate_3_dias_com_sucesso() {
		// Scenario
		ContaAPagar expected = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1022.0),
				LocalDate.of(2020, 06, 01), LocalDate.of(2020, 06, 03), 2L);
		
		RegraCalculoContaEmAtraso regraCalculoContaEmAtraso = mock(RegraCalculoContaEmAtraso.class);
		when(regraCalculoContaEmAtraso.getMulta()).thenReturn(BigDecimal.valueOf(0.02));
		when(regraCalculoContaEmAtraso.getJurosPorDia()).thenReturn(BigDecimal.valueOf(0.001));
		
		when(this.regraCalculoContaEmAtrasoService.buscarPorDiasEmAtraso(Mockito.any(DiasEmAtraso.class))).thenReturn(regraCalculoContaEmAtraso);
		
		when(this.contaAPagarRepository.save(Mockito.any(ContaAPagar.class))).thenReturn(expected);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Action
		ContaAPagar actual = this.contaAPagarService.salvar(expected, response);
		
		// Verification
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNome(), actual.getNome());
		assertEquals(expected.getValorOriginal(), actual.getValorOriginal());
		assertEquals(expected.getValorCorrigido(), actual.getValorCorrigido());
		assertEquals(expected.getQuantidadeDiasAtraso(), actual.getQuantidadeDiasAtraso());
		assertEquals(expected.getDataPagamento(), actual.getDataPagamento());
	}
	
	@Test
	public void test_deve_salvar_conta_a_pagar_que_estiver_em_atraso_superior_a_3_dias_com_sucesso() {
		// Scenario
		ContaAPagar expected = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1040.0),
				LocalDate.of(2020, 05, 29), LocalDate.of(2020, 06, 03), 5L);
		
		RegraCalculoContaEmAtraso regraCalculoContaEmAtraso = mock(RegraCalculoContaEmAtraso.class);
		when(regraCalculoContaEmAtraso.getMulta()).thenReturn(BigDecimal.valueOf(0.03));
		when(regraCalculoContaEmAtraso.getJurosPorDia()).thenReturn(BigDecimal.valueOf(0.002));
		
		when(this.regraCalculoContaEmAtrasoService.buscarPorDiasEmAtraso(Mockito.any(DiasEmAtraso.class))).thenReturn(regraCalculoContaEmAtraso);
		
		when(this.contaAPagarRepository.save(Mockito.any(ContaAPagar.class))).thenReturn(expected);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Action
		ContaAPagar actual = this.contaAPagarService.salvar(expected, response);
		
		// Verification
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNome(), actual.getNome());
		assertEquals(expected.getValorOriginal(), actual.getValorOriginal());
		assertEquals(expected.getValorCorrigido(), actual.getValorCorrigido());
		assertEquals(expected.getQuantidadeDiasAtraso(), actual.getQuantidadeDiasAtraso());
		assertEquals(expected.getDataPagamento(), actual.getDataPagamento());
	}
	
	@Test
	public void test_deve_salvar_conta_a_pagar_que_estiver_em_atraso_superior_a_5_dias_com_sucesso() {
		// Scenario
		ContaAPagar expected = new ContaAPagar(1L, "Cartao Credito", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1080.0),
				LocalDate.of(2020, 05, 26), LocalDate.of(2020, 06, 05), 10L);
		
		RegraCalculoContaEmAtraso regraCalculoContaEmAtraso = mock(RegraCalculoContaEmAtraso.class);
		when(regraCalculoContaEmAtraso.getMulta()).thenReturn(BigDecimal.valueOf(0.05));
		when(regraCalculoContaEmAtraso.getJurosPorDia()).thenReturn(BigDecimal.valueOf(0.003));
		
		when(this.regraCalculoContaEmAtrasoService.buscarPorDiasEmAtraso(Mockito.any(DiasEmAtraso.class))).thenReturn(regraCalculoContaEmAtraso);
		
		when(this.contaAPagarRepository.save(Mockito.any(ContaAPagar.class))).thenReturn(expected);

		HttpServletResponse response = mock(HttpServletResponse.class);

		// Action
		ContaAPagar actual = this.contaAPagarService.salvar(expected, response);
		
		// Verification
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNome(), actual.getNome());
		assertEquals(expected.getValorOriginal(), actual.getValorOriginal());
		assertEquals(expected.getValorCorrigido(), actual.getValorCorrigido());
		assertEquals(expected.getQuantidadeDiasAtraso(), actual.getQuantidadeDiasAtraso());
		assertEquals(expected.getDataPagamento(), actual.getDataPagamento());
	}
	
	@Test
	public void test_deve_retornar_a_lista_de_todas_as_contas_a_pagar() {
		// Scenario
		List<ContaAPagar> expected = Arrays.asList(
				new ContaAPagar(
						1L, "Cartao Credito Master", BigDecimal.valueOf(499.89), null, 
						LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 05), null),
				new ContaAPagar(
						2L, "Cartao Credito Visa", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1022.0),
						LocalDate.of(2020, 06, 01), LocalDate.of(2020, 06, 03), 2L),
				new ContaAPagar(
						3L, "Cartao Credito Bradesco", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1040.0),
						LocalDate.of(2020, 05, 29), LocalDate.of(2020, 06, 03), 5L),
				new ContaAPagar(
						4L, "Cartao Credito Nubank", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(1080.0),
						LocalDate.of(2020, 05, 26), LocalDate.of(2020, 06, 05), 10L)
				);
		
		ContaAPagar expect1 = expected.get(0); ContaAPagar expect2 = expected.get(1); ContaAPagar expect3 = expected.get(2); ContaAPagar expect4 = expected.get(3);
		
		when(this.contaAPagarRepository.findAll()).thenReturn(expected);
		
		// Action
		List<ContaAPagar> actual = this.contaAPagarService.buscar();
		
		// Verification
		assertThat(actual).isNotNull().isNotEmpty().hasSize(4);

		assertThat(actual).extracting("id", "nome", "valorOriginal", "valorCorrigido", "dataVencimento", "dataPagamento", "quantidadeDiasAtraso")
				.contains(tuple(expect1.getId(), expect1.getNome(), expect1.getValorOriginal(), expect1.getValorCorrigido(), expect1.getDataVencimento(), expect1.getDataPagamento(), expect1.getQuantidadeDiasAtraso()),
						tuple(expect2.getId(), expect2.getNome(), expect2.getValorOriginal(), expect2.getValorCorrigido(), expect2.getDataVencimento(), expect2.getDataPagamento(), expect2.getQuantidadeDiasAtraso()),
						tuple(expect3.getId(), expect3.getNome(), expect3.getValorOriginal(), expect3.getValorCorrigido(), expect3.getDataVencimento(), expect3.getDataPagamento(), expect3.getQuantidadeDiasAtraso()),
						tuple(expect4.getId(), expect4.getNome(), expect4.getValorOriginal(), expect4.getValorCorrigido(), expect4.getDataVencimento(), expect4.getDataPagamento(), expect4.getQuantidadeDiasAtraso()));
	}

}
