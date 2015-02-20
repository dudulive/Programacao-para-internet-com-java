package org.financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.financeiro.model.usuario.Usuario;
import org.financeiro.model.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	private String destinoSalvar;

	private List<Usuario> listUsuarios;

	public String actionEditar(){
		destinoSalvar = "/admin/principal";
		confirmaSenha = usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String actionExcluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(usuario);
		listUsuarios = null;
		return null;
	}
	
	public List<Usuario> getListUsuarios() {
		if (listUsuarios == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			listUsuarios = usuarioRN.findByAll();
		}
		return listUsuarios;
	}

	public String actionNovo() {
		destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
	}

	public String actionSalvar() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);
		return destinoSalvar;
	}
	
	public String actionAtivar() {
		if(usuario.getAtivo()){
			usuario.setAtivo(false);
		}else {
			usuario.setAtivo(true);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);
		return null;
	}
	
	public String actionLogin() {
		String destino = null;
		if(usuario.getLogin().length() > 0 && usuario.getSenha().length() > 0){
			UsuarioRN usuarioRN = new UsuarioRN();
			usuario = usuarioRN.findByNameAndPassword(usuario);
			if(usuario == null){
				FacesMessage message = new FacesMessage("Dados Inválidos");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}else if(usuario != null){
				destino = "/admin/principal";
			}
		}
		return destino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

}
