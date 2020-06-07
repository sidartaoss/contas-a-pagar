/*
 * @(#) ContaAPagar.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Classe persistente que representa a entidade {@link ContaAPagar}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */

@Entity
@Table(name = "conta_a_pagar")
public class ContaAPagar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_conta_a_pagar", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(name = "vl_original", nullable = false)
	private BigDecimal valorOriginal;

	@Column(name = "vl_corrigido")
	private BigDecimal valorCorrigido;

	@Column(name = "dt_vencimento", nullable = false)
	private LocalDate dataVencimento;

	@Column(name = "dt_pagamento", nullable = false)
	private LocalDate dataPagamento;

	@Column(name = "qtd_dias_atraso")
	private Long quantidadeDiasAtraso;

	@Version
	@Column(name = "dt_modificacao")
	private Timestamp version;

	public ContaAPagar() {
	}

	public ContaAPagar(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public ContaAPagar(Long id, String nome, BigDecimal valorOriginal, BigDecimal valorCorrigido,
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
				"ContaAPagar [id=%s, nome=%s, valorOriginal=%s, valorCorrigido=%s, dataVencimento=%s, dataPagamento=%s, quantidadeDiasAtraso=%s, version=%s]",
				id, nome, valorOriginal, valorCorrigido, dataVencimento, dataPagamento, quantidadeDiasAtraso, version);
	}

}
