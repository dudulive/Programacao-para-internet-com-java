package org.compras.model.compra;

import java.util.List;

public interface ICompra {
    
	public void salvar(Compra compra);
	public 	void atualizar(Compra compra);
	public void excluir (Compra compra);
	public List<Compra> findByAll();
}
