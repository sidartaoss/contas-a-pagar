/*
 * @(#) ContaAPagarMapper.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.deliverit.contasapagar.dto.ContaAPagarDto;
import com.deliverit.contasapagar.model.ContaAPagar;

/**
 * Interface mapper para objetos {@link ContaAPagar}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Mapper
public interface ContaAPagarMapper {

	ContaAPagarMapper MAPPER = Mappers.getMapper(ContaAPagarMapper.class);

	/**
	 * Método responsável por converter um objeto {@link ContaAPagar} para
	 * {@link ContaAPagarDto}.
	 * 
	 * @param contaAPagar o objeto {@link ContaAPagar}.
	 * @return o objeto {@link ContaAPagarDto}.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nome", target = "nome")
	@Mapping(source = "valorOriginal", target = "valorOriginal")
	@Mapping(source = "valorCorrigido", target = "valorCorrigido")
	@Mapping(source = "dataVencimento", target = "dataVencimento")
	@Mapping(source = "dataPagamento", target = "dataPagamento")
	@Mapping(source = "quantidadeDiasAtraso", target = "quantidadeDiasAtraso")
	ContaAPagarDto toContaAPagarDto(ContaAPagar contaAPagar);

	/**
	 * Método responsável por converter um objeto {@link ContaAPagar} para
	 * {@link ContaAPagarDto} ignorando os atributos {@code id} e
	 * {@code dataVencimento}.
	 * 
	 * @param contaAPagar o objeto {@link ContaAPagar}.
	 * @return o objeto {@link ContaAPagarDto}.
	 */
	@Mapping(source = "id", target = "id", ignore = true)
	@Mapping(source = "nome", target = "nome")
	@Mapping(source = "valorOriginal", target = "valorOriginal")
	@Mapping(source = "valorCorrigido", target = "valorCorrigido")
	@Mapping(source = "dataVencimento", target = "dataVencimento", ignore = true)
	@Mapping(source = "dataPagamento", target = "dataPagamento")
	@Mapping(source = "quantidadeDiasAtraso", target = "quantidadeDiasAtraso")
	ContaAPagarDto toContaAPagarDtoIgnoringFields(ContaAPagar contaAPagar);

	/**
	 * Método responsável por converter um objeto {@link ContaAPagarDto} para
	 * {@link ContaAPagar}.
	 * 
	 * @param contaAPagarDto o objeto {@link ContaAPagarDto}.
	 * @return o objeto {@link ContaAPagar}.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nome", target = "nome")
	@Mapping(source = "valorOriginal", target = "valorOriginal")
	@Mapping(source = "valorCorrigido", target = "valorCorrigido")
	@Mapping(source = "dataVencimento", target = "dataVencimento")
	@Mapping(source = "dataPagamento", target = "dataPagamento")
	@Mapping(source = "quantidadeDiasAtraso", target = "quantidadeDiasAtraso")
	ContaAPagar toContaAPagar(ContaAPagarDto contaAPagarDto);
}
