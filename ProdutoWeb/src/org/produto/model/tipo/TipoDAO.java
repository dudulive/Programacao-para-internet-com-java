package org.produto.model.tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.produto.model.produto.Produto;
import org.produto.util.ConnectionFactory;

public class TipoDAO implements ITipoDAO {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Tipo tipo) {
		String sql = "INSERT INTO tipo (descricao) " + " VALUES (?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, tipo.getDescricao());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public void update(Tipo tipo) {
		String sql = "UPDATE tipo SET descricao = ? WHERE id = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, tipo.getDescricao());
			ps.setLong(2, tipo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public void delete(Tipo tipo) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tipo WHERE id = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, tipo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método delete. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps);
		}
	}

	@Override
	public List<Tipo> selectAll() {
		List<Tipo> lsTipos = null;
		String sql = "SELECT id, descricao FROM tipo ORDER BY id ";

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsTipos = new ArrayList<Tipo>();
			while (rs.next()) {
				Tipo tipo = new Tipo();
				tipo.setId(rs.getLong("id"));
				tipo.setDescricao(rs.getString("descricao"));
				lsTipos.add(tipo);
			}
		} catch (Exception e) {
			System.err.println("Erro no método selectAll de Tipo");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return lsTipos;
	}

	@Override
	public List<Produto> selectAllProdutosTipo(Tipo tipo) {
		List<Produto> lsProdutos = null;
		String sql = "SELECT codigo,nome,marca,preco FROM produto WHERE tipo = ? ";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, tipo.getId());
			rs = ps.executeQuery();
			lsProdutos = new ArrayList<Produto>();
			while (rs.next()) {
				Produto produto = new Produto();
				tipo = new Tipo();
				produto.setCodigo(rs.getLong("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setMarca(rs.getString("marca"));
				produto.setPreco(rs.getDouble("preco"));
				lsProdutos.add(produto);
			}
			tipo.setProdutos(lsProdutos);
		} catch (Exception e) {
			System.err.println("Erro no método selectAllProdutosTipo de Tipo");
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(connection, ps, rs);
		}
		return tipo.getProdutos();
	}
}
