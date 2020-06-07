/*
 * @(#) ContasAPagarRepository.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverit.contasapagar.model.ContaAPagar;

/**
 * Interface do repositório para a entidade {@link ContaAPagar}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Repository
public interface ContaAPagarRepository extends JpaRepository<ContaAPagar, Long> {

}
