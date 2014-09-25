package br.com.tamandua.DAO;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.FuncionarioModel;

public class LoginDAO extends DAO {
	
	public boolean autorizadorUsuario(FuncionarioModel funcionario) {
		boolean result = false;
		String sql = "select * from funcionario f where f.login = ? and f.senha = ? limit 1";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getLogin());
			stmt.setString(2, funcionario.getSenha());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Erro ao autorizar funcionario " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao autorizar funcionario " + e);
			}
		}
		return result;
	}
	
	public FuncionarioModel verificarTipoFuncionario(FuncionarioModel funcionario) {
		String sql = "select f.nome, f.funcao, f.cpf from funcionario f where f.login = ? and f.senha = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getLogin());
			stmt.setString(2, funcionario.getSenha());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				funcionario.setFuncao(rs.getString("funcao"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
			}
		} catch (SQLException e) {
			log.error("Erro ao verificar tipo de funcionario " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao verificar tipo de funcionario " + e);
			}
		}
		return funcionario;
	}
	
	public boolean verificarLoginAtivo(FuncionarioModel funcionario) {
		short status = 0;
		String sql = "select f.status from funcionario f where f.login = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getLogin());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				status = rs.getShort("status");
			}
		} catch (SQLException e) {
			log.error("Erro ao verificar status da conta do funcionario " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao verificar status da conta do funcionario " + e);
			}
		}
		int t = (int) (status); 
		return (t == ATIVO) ? true : false;
	}
}
