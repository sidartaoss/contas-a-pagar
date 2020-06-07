/*
 * @(#) RegraCalculoContaEmAtrasoService.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverit.contasapagar.model.DiasEmAtraso;
import com.deliverit.contasapagar.model.RegraCalculoContaEmAtraso;
import com.deliverit.contasapagar.repository.RegraCalculoContaEmAtrasoRepository;
import com.deliverit.contasapagar.service.exception.BusinessException;

/**
 * Classe de negócio que representa o componente de serviço
 * {@link RegraCalculoContaEmAtrasoService}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Service
public class RegraCalculoContaEmAtrasoService {

	@Autowired
	private RegraCalculoContaEmAtrasoRepository regraCalculoContaEmAtrasoRepository;

	/**
	 * Método responsável por buscar um objeto {@link RegraCalculoContaEmAtraso}
	 * pelo valor do atributo {@code diasEmAtraso}.
	 * 
	 * @param diasEmAtraso o enum {@link DiasEmAtraso}.
	 * @return um objeto {@link RegraCalculoContaEmAtraso}.
	 */
	RegraCalculoContaEmAtraso buscarPorDiasEmAtraso(DiasEmAtraso diasEmAtraso) {
		if (diasEmAtraso == null) {
			throw new BusinessException("regras-calculos-em-atraso-1", HttpStatus.BAD_REQUEST);
		}
		return this.regraCalculoContaEmAtrasoRepository.findByDiasEmAtraso(diasEmAtraso);
	}
}
