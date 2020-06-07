/*
 * @(#) RegraCalculoContaEmAtrasoRepository.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverit.contasapagar.model.DiasEmAtraso;
import com.deliverit.contasapagar.model.RegraCalculoContaEmAtraso;

/**
 * Interface do repositório para a entidade {@link RegraCalculoContaEmAtraso}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Repository
public interface RegraCalculoContaEmAtrasoRepository extends JpaRepository<RegraCalculoContaEmAtraso, Long> {

	/**
	 * Método responsável por buscar um objeto {@link RegraCalculoContaEmAtraso}
	 * pelo atributo {@code diasEmAtraso} passado como parâmetro.
	 * 
	 * @param diasEmAtraso o enum {@link DiasEmAtraso}.
	 * @return um objeto {@link RegraCalculoContaEmAtraso}.
	 */
	RegraCalculoContaEmAtraso findByDiasEmAtraso(DiasEmAtraso diasEmAtraso);
}
