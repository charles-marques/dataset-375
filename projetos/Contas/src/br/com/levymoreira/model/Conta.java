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
 * Pojo representando uma conta, configurado com annotation para usar o
 * hibernate.
 * 
 * @author levy
 * @since 21/01/2012
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CONTAS")
public class Conta implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@Column(name = "total_parcelas")
	private int totalParcelas;
   
	@ManyToOne()
	@JoinColumn(name="id_local_pagamento")    
	private LocalPagamento localPagamento; 
	
    @ManyToOne()
    @JoinColumn(name="id_local_compra")    
    private LocalCompra localCompra;      
   
    @Temporal(TemporalType.DATE)
	@Column(name = "data_compra")	
	private Date dataCompra;
	
	//Contructor Default
    
	public Conta(){}
	
	//Constructor
	
	public Conta(String descricao, Date dataCompra, int totalParcelas, BigDecimal valorTotal, LocalPagamento localPagamento, LocalCompra localCompra){
		this.descricao = descricao;
		this.dataCompra = dataCompra;
		this.totalParcelas = totalParcelas;
		this.valorTotal = valorTotal;
		this.localPagamento = localPagamento;
		this.localCompra = localCompra;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Getters and Settes

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getTotalParcelas() {
		return totalParcelas;
	}

	public void setTotalParcelas(int totalParcelas) {
		this.totalParcelas = totalParcelas;
	}

	public LocalPagamento getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(LocalPagamento localPagamento) {
		this.localPagamento = localPagamento;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Integer getId() {
		return id;
	}

	public LocalCompra getLocalCompra() {
		return localCompra;
	}

	public void setLocalCompra(LocalCompra localCompra) {
		this.localCompra = localCompra;
	}	
	
}
