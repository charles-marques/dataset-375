package br.com.levymoreira.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pojo representando uma parcela, configurado com annotation para usar o
 * hibernate.
 * 
 * @author levy
 * @since 21/01/2012
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PARCELAS")
public class Parcela implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "id_conta")
	private Conta conta;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento")
	private Date dataPagamento;
	
	@Column(name = "numero_parcela")
	private int numeroParcela;
	
	@Column(name = "valor_parcela")
	private BigDecimal valorParcela;
	
	@Column(name = "valor_pago")
	private BigDecimal valorPago;	
	
	//Contructor Default
  
	public Parcela(){}
	
	//Contructor
	
	public Parcela(Conta conta, Date dataPagamento, int numeroParcela, BigDecimal valorParela, BigDecimal valorPago){
		this.conta = conta;
		this.numeroParcela = numeroParcela;
		this.valorPago = valorPago;
		this.valorParcela = valorParela;
		this.dataPagamento = dataPagamento;
	}
	
	/**
	 * @author Levy Moreira
     * @since 10/03/2012
	 */	
	@Override
	public String toString() {
		return "Parcela: ID-" + this.id;
	}
	
	//Getters and Setters

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Integer getId() {
		return id;
	}
}
