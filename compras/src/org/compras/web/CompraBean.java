package org.compras.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.compras.model.compra.Compra;
import org.compras.model.compra.CompraRN;


@ManagedBean(name = "compraBean")
@RequestScoped
public class CompraBean {

	private Compra compra = new Compra();
	private String destinoSalvar;
	private String somaTotal;

	public String getSomaTotal() {
		CompraRN compraRN = new CompraRN();
		String somaTotal = String.valueOf(compraRN.somaTotal());
		return somaTotal;
	}

	public void setSomaTotal(String somaTotal) {
		this.somaTotal = somaTotal;
	}

	private List<Compra> listCompras;

	public String actionExcluir() {
		CompraRN compraRN = new CompraRN();
		compraRN.excluir(compra);
		listCompras = null;
		return null;
	}

	public List<Compra> getListCompras() {
		if (listCompras == null) {
			CompraRN compraRN = new CompraRN();
			listCompras = compraRN.findByAll();
		}
		return listCompras;
	}

	public String actionNovo() {
		destinoSalvar = "compraSucesso";
		this.compra = new Compra();
		this.compra.setAtivo(true);
		return "publico/compra";
	}

	public String actionSalvar() {
		CompraRN compraRN = new CompraRN();
		compraRN.salvar(compra);
		return destinoSalvar;
	}
	
	public String actionEditar(){
		destinoSalvar = "/index";
		return "/publico/compra";
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
  
}
