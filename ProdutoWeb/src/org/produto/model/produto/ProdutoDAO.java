package org.produto.model.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.produto.model.tipo.Tipo;
import org.produto.util.ConnectionFactory;

public class ProdutoDAO implements IProdutoDAO {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Produto produto) {
		String sql = "INSERT INTO produto (nome, marca, preco, tipo) "
				+ " VALUES (?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getMarca());
			ps.setDouble(3, produto.getPreco());
			ps.setLong(4, produto.getTipo().getId());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}

	}

	@Override
	public void update(Produto produto) {
		String sql = "UPDATE produto SET nome = ?, marca = ?, "
				+ " preco = ?, tipo = ? WHERE codigo = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getMarca());
			ps.setDouble(3, produto.getPreco());
			ps.setDouble(4, produto.getTipo().getId());
			ps.setLong(5, produto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public void delete(Produto produto) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM produto WHERE codigo = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, produto.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método delete. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public List<Produto> selectAll() {
		List<Produto> lsProdutos = null;
		String sql = "SELECT produto.codigo, produto.nome, produto.marca, "
				+ "produto.preco, tipo.id, tipo.descricao "
				+ "FROM tipo, produto " + " WHERE tipo.id = produto.tipo ORDER BY produto.codigo";

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsProdutos = new ArrayList<Produto>();
			while (rs.next()) {
				Produto produto = new Produto();
				Tipo tipo = new Tipo();
				produto.setCodigo(rs.getLong("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setMarca(rs.getString("marca"));
				produto.setPreco(rs.getDouble("preco"));
				tipo.setId(rs.getLong("id"));
				tipo.setDescricao(rs.getString("descricao"));
				produto.setTipo(tipo);
				lsProdutos.add(produto);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAll de lsProdutos");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsProdutos;
	}

}
