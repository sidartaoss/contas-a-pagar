/*
 * @(#) ContaAPagarResource.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.resource;

import static com.deliverit.contasapagar.mapper.ContaAPagarMapper.MAPPER;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliverit.contasapagar.dto.ContaAPagarDto;
import com.deliverit.contasapagar.model.ContaAPagar;
import com.deliverit.contasapagar.service.ContaAPagarService;

import io.swagger.annotations.ApiOperation;

/**
 * Classe REST Controller que representa o recurso {@link ContaAPagarResource}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@RestController
public class ContaAPagarResource {

	@Autowired
	private ContaAPagarService contaAPagarService;

	/**
	 * Método responsável por cadastar uma Conta A Pagar.
	 * 
	 * @param contaAPagarDto o objeto {@link ContaAPagarDto}.
	 * @return o objeto {@link ContaAPagarDto}.
	 */
	@ApiOperation("Método responsável por cadastrar uma Conta A Pagar.")
	@PostMapping("/v1/contas-a-pagar")
	@ResponseStatus(CREATED)
	public ContaAPagarDto cadastrar(@Valid @RequestBody ContaAPagarDto contaAPagarDto, HttpServletResponse response) {
		ContaAPagar contaAPagar = MAPPER.toContaAPagar(contaAPagarDto);
		ContaAPagar contaAPagarSalva = contaAPagarService.salvar(contaAPagar, response);
		return MAPPER.toContaAPagarDto(contaAPagarSalva);
	}

	/**
	 * Método responsável por buscar a listagem de todas as Contas A Pagar.
	 * 
	 * @return a listagem de todas as Contas A Pagar.
	 */
	@ApiOperation("Método responsável por buscar a listagem de todas as Contas A Pagar.")
	@GetMapping("/v1/contas-a-pagar")
	public List<ContaAPagarDto> buscar() {
		return this.contaAPagarService.buscar().stream().map(MAPPER::toContaAPagarDtoIgnoringFields)
				.collect(Collectors.toList());
	}
}
