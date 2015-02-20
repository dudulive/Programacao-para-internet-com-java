package org.compras.util;

import org.compras.model.compra.CompraDAO;



public class DAOFactory {
	
	public static CompraDAO criaCompraDAO(){
		CompraDAO compraDAO = new CompraDAO();
		compraDAO.setConexao(ConnectionFactory.getConexao());
		return compraDAO;
	}

}
