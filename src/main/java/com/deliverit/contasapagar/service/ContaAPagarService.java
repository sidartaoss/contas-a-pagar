/*
 * @(#) ContasAPagarService.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverit.contasapagar.event.CreatedResourceEvent;
import com.deliverit.contasapagar.model.ContaAPagar;
import com.deliverit.contasapagar.model.DiasEmAtraso;
import com.deliverit.contasapagar.model.RegraCalculoContaEmAtraso;
import com.deliverit.contasapagar.repository.ContaAPagarRepository;
import com.deliverit.contasapagar.service.exception.BusinessException;

/**
 * Classe de negócio que representa o componente de serviço
 * {@link ContaAPagarService}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Service
public class ContaAPagarService {

	@Autowired
	private ContaAPagarRepository contaAPagarRepository;

	@Autowired
	private RegraCalculoContaEmAtrasoService regraCalculoContaEmAtrasoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * Método responsável por salvar um novo objeto {@link ContaAPagar}.
	 * 
	 * @param contaAPagar o objeto {@link ContaAPagar}.
	 * @param response    o objeto {@link HttpServletResponse}.
	 * @return o objeto {@link ContaAPagar} já persistido, com o id e version
	 *         preenchidos.
	 */
	public ContaAPagar salvar(@Valid ContaAPagar contaAPagar, HttpServletResponse response) {
		final String nome = contaAPagar.getNome();
		if (nome == null || nome.trim().isEmpty()) {
			throw new BusinessException("contas-a-pagar-1", HttpStatus.BAD_REQUEST);
		}
		final BigDecimal valorOriginal = contaAPagar.getValorOriginal();
		if (valorOriginal == null) {
			throw new BusinessException("contas-a-pagar-3", HttpStatus.BAD_REQUEST);
		}
		final LocalDate dataVencimento = contaAPagar.getDataVencimento();
		if (dataVencimento == null) {
			throw new BusinessException("contas-a-pagar-4", HttpStatus.BAD_REQUEST);
		}
		final LocalDate dataPagamento = contaAPagar.getDataPagamento();
		if (dataPagamento == null) {
			throw new BusinessException("contas-a-pagar-5", HttpStatus.BAD_REQUEST);
		}
		if (dataPagamento.isAfter(dataVencimento)) {
			long quantidadeDiasAtraso = ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
			RegraCalculoContaEmAtraso regraCalculo = this.regraCalculoContaEmAtrasoService
					.buscarPorDiasEmAtraso(this.obterDiasEmAtraso(quantidadeDiasAtraso));
			BigDecimal valorMulta = valorOriginal.multiply(regraCalculo.getMulta());
			BigDecimal valorJuros = valorOriginal.multiply(regraCalculo.getJurosPorDia())
					.multiply(BigDecimal.valueOf(quantidadeDiasAtraso));
			BigDecimal valorCorrigido = valorOriginal.add(valorMulta).add(valorJuros);
			contaAPagar.setValorCorrigido(valorCorrigido.setScale(2, BigDecimal.ROUND_HALF_UP));
			contaAPagar.setQuantidadeDiasAtraso(quantidadeDiasAtraso);
		}
		ContaAPagar contaAPagarSalva = this.contaAPagarRepository.save(contaAPagar);
		this.publisher.publishEvent(new CreatedResourceEvent(this, response, contaAPagarSalva.getId()));
		return contaAPagarSalva;
	}

	/**
	 * Método responsável por buscar a listagem de todos os objetos
	 * {@link ContaAPagar}.
	 * 
	 * @return a listagem de todos os objetos {@link ContaAPagar}.
	 */
	public List<ContaAPagar> buscar() {
		return this.contaAPagarRepository.findAll();
	}

	/**
	 * Método responsável por obter o enum {@link DiasEmAtraso} de acordo com a
	 * quantidade de dias em atraso passado como parâmetro.
	 * 
	 * @param quantidadeDiasAtraso a quantidade de dias em atraso.
	 * @return o enum {@link DiasEmAtraso}.
	 */
	private DiasEmAtraso obterDiasEmAtraso(long quantidadeDiasAtraso) {
		if (quantidadeDiasAtraso <= 3) {
			return DiasEmAtraso.ATE_3_DIAS;
		}
		return quantidadeDiasAtraso > 3 && quantidadeDiasAtraso <= 5 ? DiasEmAtraso.SUPERIOR_A_3_DIAS
				: DiasEmAtraso.SUPERIOR_A_5_DIAS;
	}

}
