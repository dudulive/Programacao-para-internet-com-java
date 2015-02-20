package org.produto.model.tipo;

import java.util.List;

import org.produto.model.produto.Produto;

public interface ITipoDAO {

	public void insert(Tipo tipo);
	public void update(Tipo tipo);
	public void delete(Tipo tipo);
	public List<Tipo> selectAll();
	public List<Produto> selectAllProdutosTipo(Tipo tipo);
}
