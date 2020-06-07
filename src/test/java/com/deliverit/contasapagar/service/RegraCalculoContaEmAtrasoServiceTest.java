/*
 * @(#) RegraCalculoContaEmAtrasoServiceTest.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.deliverit.contasapagar.repository.RegraCalculoContaEmAtrasoRepository;
import com.deliverit.contasapagar.service.exception.BusinessException;

/**
 * Classe de testes unitários para o serviço
 * {@link RegraCalculoContaEmAtrasoService}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@RunWith(MockitoJUnitRunner.class)
public class RegraCalculoContaEmAtrasoServiceTest {

	@InjectMocks
	private RegraCalculoContaEmAtrasoService regraCalculoContaEmAtrasoService;

	@Mock
	private RegraCalculoContaEmAtrasoRepository regraCalculoContaEmAtrasoRepository;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void test_deve_disparar_excecao_quando_buscar_por_dias_em_atraso_e_dias_em_atraso_nao_for_informado() {
		// Scenario
		// Verification
		exception.expect(BusinessException.class);

		// Action
		this.regraCalculoContaEmAtrasoService.buscarPorDiasEmAtraso(null);
	}
}
