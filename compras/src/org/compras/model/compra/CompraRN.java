package org.compras.model.compra;

import java.util.List;

import org.compras.util.DAOFactory;

public class CompraRN {
	
	private ICompra compraDAO;

	public CompraRN() {
		this.compraDAO = DAOFactory.criaCompraDAO();
	}

	public void salvar(Compra compra) {
		Long id = compra.getId();
		if (id == null || id == 0){
			compraDAO.salvar(compra);
		} else {
			compraDAO.atualizar(compra);
		}
	}
	
	public List<Compra> findByAll(){
		return compraDAO.findByAll();
	}
	
	public void excluir(Compra compra) {
	    compraDAO.excluir(compra);
	}
	
	public Double somaTotal(){
		List<Compra> listCompras = compraDAO.findByAll();
		Double somaTotal = 0.0;
		for (Compra compra : listCompras) {
			somaTotal += compra.getPrecoEst();
		}
		return somaTotal;
	}

}
