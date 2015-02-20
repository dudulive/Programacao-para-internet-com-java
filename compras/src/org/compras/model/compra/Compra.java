package org.compras.model.compra;

import java.io.Serializable;

public class Compra implements Serializable {
   
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String produto;
	private String unidadeCompra;
	private Double qtdMes;
	private Double qtdCompra;
	private Double precoEst;
	
	//Adicionado variável ativo
	private Boolean ativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getUnidadeCompra() {
		return unidadeCompra;
	}
	public void setUnidadeCompra(String unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}
	public Double getQtdMes() {
		return qtdMes;
	}
	public void setQtdMes(Double qtdMes) {
		this.qtdMes = qtdMes;
	}
	public Double getQtdCompra() {
		return qtdCompra;
	}
	public void setQtdCompra(Double qtdCompra) {
		this.qtdCompra = qtdCompra;
	}
	public Double getPrecoEst() {
		return precoEst;
	}
	public void setPrecoEst(Double precoEst) {
		this.precoEst = precoEst;
	}
	
	//Adicionado GET e SET da variável ativo
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
