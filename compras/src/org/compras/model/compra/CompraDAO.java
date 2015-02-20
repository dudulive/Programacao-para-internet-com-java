package org.compras.model.compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.compras.util.ConnectionFactory;




public class CompraDAO implements ICompra {

	private Connection conexao;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void salvar(Compra compra) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO compras (produto, unidadeCompra, qtdMes, qtdCompra ,precoEst) "
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, compra.getProduto());
			ps.setString(2, compra.getUnidadeCompra());
			ps.setDouble(3, compra.getQtdMes());
			ps.setDouble(4, compra.getQtdCompra());
			ps.setDouble(5, compra.getPrecoEst());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void atualizar(Compra compra) {
		// TODO Auto-generated method stub
		String sql = "UPDATE compras SET produto = ?, unidadeCompra = ?, "
				+ " qtdMes = ?, qtdCompra = ?, precoEst = ? WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps = conexao.prepareStatement(sql);
			ps.setString(1, compra.getProduto());
			ps.setString(2, compra.getUnidadeCompra());
			ps.setDouble(3, compra.getQtdMes());
			ps.setDouble(4, compra.getQtdCompra());
			ps.setDouble(5, compra.getPrecoEst());
			ps.setLong(6, compra.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void excluir(Compra compra) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM compras WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, compra.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método excluir. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}
	
	@Override
	public List<Compra> findByAll() {
		List<Compra> lsCompras = null;
		String sql = "SELECT id, produto, unidadeCompra, qtdMes, qtdCompra ,precoEst FROM compras";
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			lsCompras = new ArrayList<Compra>();
			while (rs.next()) {
				Compra compra = new Compra();
				compra.setId(rs.getLong("id"));
				compra.setProduto(rs.getString("produto"));
				compra.setUnidadeCompra(rs.getString("unidadeCompra"));
				compra.setQtdMes(rs.getDouble("qtdMes"));
				compra.setQtdCompra(rs.getDouble("qtdCompra"));
				compra.setPrecoEst(rs.getDouble("precoEst"));
				lsCompras.add(compra);
			}
		} catch (Exception e) {
			System.err.println("Erro no método findByAll. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps, rs);
		}
		return lsCompras;
	}
}
