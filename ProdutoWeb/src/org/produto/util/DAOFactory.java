package org.produto.util;

import org.produto.model.produto.ProdutoDAO;
import org.produto.model.tipo.TipoDAO;

public class DAOFactory {

	public static TipoDAO criarTipoDAO(){
		TipoDAO tipoDAO = new TipoDAO();
		tipoDAO.setConnection(ConnectionFactory.getConexao());
		return tipoDAO;
	}
	
	public static ProdutoDAO criarProdutoDAO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
	    produtoDAO.setConnection(ConnectionFactory.getConexao());
		return produtoDAO;
	}
}