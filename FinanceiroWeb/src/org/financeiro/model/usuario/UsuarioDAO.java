package org.financeiro.model.usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.financeiro.util.ConnectionFactory;

public class UsuarioDAO implements IUsuarioDAO {

	private Connection conexao;
	private PreparedStatement ps;
	private ResultSet rs;

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO usuario (nome, dataNascimento, celular, email, idioma, login, senha,ativo) "
				+ " VALUES (?, ?, ?, ?, ?, ? , MD5(?), ?)";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			ps.setString(3, usuario.getCelular());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getIdioma());
			ps.setString(6, usuario.getLogin());
			ps.setString(7, usuario.getSenha());
			ps.setBoolean(8, usuario.getAtivo());
			ps.execute();
		} catch (Exception e) {
			System.err.println("Erro na inserção da linha. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "UPDATE usuario SET nome = ?, dataNascimento = ?, "
				+ " celular = ?, email = ?, idioma = ?, login = ?, senha = MD5(?), "
				+ " ativo = ? WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			ps.setString(3, usuario.getCelular());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getIdioma());
			ps.setString(6, usuario.getLogin());
			ps.setString(7, usuario.getSenha());
			ps.setBoolean(8, usuario.getAtivo());
			ps.setLong(9, usuario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método atualizar. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM usuario WHERE id = ?";
		try {
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, usuario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Erro no método excluir. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps);
		}
	}

	@Override
	public Usuario findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByNameAndPassword(String login, String password) {
		Usuario usuario = null;
		String sql = "SELECT id, nome, dataNascimento, celular, email, idioma, "
				+ " login, senha, ativo FROM usuario WHERE login = ? AND senha = ?";
		try{
			ps = conexao.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dataNascimento"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdioma(rs.getString("idioma"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
			}
		}catch (Exception e) {
			System.err.println("Erro no método findByNameAndPassword. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps, rs);
		}
		return usuario;
	}

	@Override
	public List<Usuario> findByAll() {
		List<Usuario> lsUsuarios = null;
		String sql = "SELECT id, nome, dataNascimento, celular, email, idioma, "
				+ " login, senha, ativo FROM usuario";
		try {
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			lsUsuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dataNascimento"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdioma(rs.getString("idioma"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				lsUsuarios.add(usuario);
			}
		} catch (Exception e) {
			System.err.println("Erro no método findByAll. " + e.getMessage());
		} finally {
			ConnectionFactory.fecharConexao(conexao, ps, rs);
		}
		return lsUsuarios;
	}

}
