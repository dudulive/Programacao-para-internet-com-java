package org.produto.model.tipo;

import java.util.List;

import org.produto.model.produto.Produto;
import org.produto.util.DAOFactory;

public class TipoRN {

	private ITipoDAO tipoDAO;
	
	public TipoRN(){
		this.tipoDAO = DAOFactory.criarTipoDAO();
	}
	
	public void insert(Tipo tipo){
		Long id = tipo.getId();
		if (id == null || id == 0){
			this.tipoDAO.insert(tipo);
		} else {
			this.tipoDAO.update(tipo);
		}
	}
	
	public List<Tipo> selectAll() {
	    return this.tipoDAO.selectAll();
	}
	
	public void delete(Tipo tipo) {
		this.tipoDAO.delete(tipo);
	}
	
	public List<Produto> selectAllProdutosTipo(Tipo tipo) {
	    return this.tipoDAO.selectAllProdutosTipo(tipo);
	}
}
