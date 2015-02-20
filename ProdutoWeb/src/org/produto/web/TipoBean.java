package org.produto.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.produto.model.tipo.Tipo;
import org.produto.model.tipo.TipoRN;

@ManagedBean
@RequestScoped
public class TipoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Tipo> listTipos;
	private Tipo tipo = new Tipo();
	private String destinoSalvar;

	public List<Tipo> getListTipos() {
		if (this.listTipos == null) {
			TipoRN tipoRN = new TipoRN();
			this.listTipos = tipoRN.selectAll();
		}
		return listTipos;
	}

	public void setListTipos(List<Tipo> listTipos) {
		this.listTipos = listTipos;
	}
	
	public String actionSalvar(){
		TipoRN tipoRN = new TipoRN();
		tipoRN.insert(tipo);
		return destinoSalvar;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String actionEditar(){
		destinoSalvar = "/tipo/lista_tipo";
		return "/tipo/form_tipo";
	}
	
	public String actionExcluir() {
		TipoRN tipoRN = new TipoRN();
		tipo.setProdutos(tipoRN.selectAllProdutosTipo(tipo));
		if(tipo.getProdutos().size() > 0){
			FacesMessage message = new FacesMessage("Exclusão não permitida esse Tipo esta associado a varios Produtos!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}else {
			tipoRN = new TipoRN();
			tipoRN.delete(tipo);
			listTipos = null;
		}
		return null;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String actionManterTipo(){
		destinoSalvar = "tipoSucesso";
		this.tipo = new Tipo();
		return "form_tipo";
	}
}
