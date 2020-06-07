/*
 * @(#) ContaAPagarDto.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deliverit.contasapagar.model.ContaAPagar;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe DTO para a entidade {@link ContaAPagar}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@ApiModel(description = "Classe DTO que representa a entidade ContaAPagar.")
@JsonInclude(Include.NON_NULL)
public class ContaAPagarDto {

	@ApiModelProperty(notes = "O identificador do objeto (PK).")
	private Long id;

	@ApiModelProperty(notes = "O nome da conta a pagar.")
	@NotBlank(message = "contas-a-pagar-1")
	@Size(min = 3, max = 50, message = "contas-a-pagar-2")
	private String nome;

	@ApiModelProperty(notes = "O valor original da conta a pagar.")
	@NotNull(message = "contas-a-pagar-3")
	private BigDecimal valorOriginal;

	@ApiModelProperty(notes = "O valor corrigido da conta a pagar.")
	private BigDecimal valorCorrigido;

	@ApiModelProperty(notes = "A data de vencimento da conta a pagar.")
	@NotNull(message = "contas-a-pagar-4")
	private LocalDate dataVencimento;

	@ApiModelProperty(notes = "A data de pagamento da conta a pagar.")
	@NotNull(message = "contas-a-pagar-5")
	private LocalDate dataPagamento;

	@ApiModelProperty(notes = "A quantidade de dias em atraso da conta a pagar.")
	private Long quantidadeDiasAtraso;

	public ContaAPagarDto() {
	}

	public ContaAPagarDto(Long id, String nome, BigDecimal valorOriginal, BigDecimal valorCorrigido,
			LocalDate dataVencimento, LocalDate dataPagamento, Long quantidadeDiasAtraso) {
		this.id = id;
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.valorCorrigido = valorCorrigido;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public BigDecimal getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(BigDecimal valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Long getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(Long quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	@Override
	public String toString() {
		return String.format(
				"ContaAPagarDto [id=%s, nome=%s, valorOriginal=%s, valorCorrigido=%s, dataVencimento=%s, dataPagamento=%s, quantidadeDiasAtraso=%s]",
				id, nome, valorOriginal, valorCorrigido, dataVencimento, dataPagamento, quantidadeDiasAtraso);
	}

}
