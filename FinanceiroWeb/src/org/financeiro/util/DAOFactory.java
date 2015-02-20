package org.financeiro.util;

import org.financeiro.model.usuario.UsuarioDAO;

public class DAOFactory {
	
	public static UsuarioDAO criaUsuarioDAO(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setConexao(ConnectionFactory.getConexao());
		return usuarioDAO;
	}

}
