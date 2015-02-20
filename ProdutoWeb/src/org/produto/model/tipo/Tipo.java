package org.produto.model.tipo;

import java.util.List;

import org.produto.model.produto.Produto;

public class Tipo {

	private Long id;
	private String descricao;
	private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Tipo [id=" + id + ", descricao=" + descricao + ", produtos="
				+ produtos + "]";
	}
	
}
