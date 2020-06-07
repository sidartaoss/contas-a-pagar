/*
 * @(#) RegraCalculoContaEmAtraso.java      1.00    06/06/2020
 * Copyrights (c) 2020 Deliver IT.
 * Todos os direitos reservados.
 */
package com.deliverit.contasapagar.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe persistente que representa a entidade
 * {@link RegraCalculoContaEmAtraso}.
 * 
 * @author Sidarta Silva (semprebono@gmail.com)
 * @version $Revision$
 */
@Entity
@Table(name = "regra_calc_cta_em_atraso")
public class RegraCalculoContaEmAtraso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_regra_calc_cta_em_atraso", nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "dias_em_atraso", nullable = false)
	private DiasEmAtraso diasEmAtraso;

	@Column(nullable = false)
	private BigDecimal multa;

	@Column(name = "juros_dia", nullable = false)
	private BigDecimal jurosPorDia;

	public RegraCalculoContaEmAtraso() {
	}

	public RegraCalculoContaEmAtraso(Long id, DiasEmAtraso diasEmAtraso, BigDecimal multa, BigDecimal jurosPorDia) {
		this.id = id;
		this.diasEmAtraso = diasEmAtraso;
		this.multa = multa;
		this.jurosPorDia = jurosPorDia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiasEmAtraso getDiasEmAtraso() {
		return diasEmAtraso;
	}

	public void setDiasEmAtraso(DiasEmAtraso diasEmAtraso) {
		this.diasEmAtraso = diasEmAtraso;
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public BigDecimal getJurosPorDia() {
		return jurosPorDia;
	}

	public void setJurosPorDia(BigDecimal jurosPorDia) {
		this.jurosPorDia = jurosPorDia;
	}

	@Override
	public String toString() {
		return String.format("RegraCalculoContaEmAtraso [id=%s, diasEmAtraso=%s, multa=%s, jurosPorDia=%s]", id,
				diasEmAtraso, multa, jurosPorDia);
	}

}
