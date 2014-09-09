package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;

public class ContaDAO extends DAO {
	
	public boolean valida_nroconta(String nroConta) {
		boolean result = false;
		String sql = "select * from conta where nroconta = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nroConta);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Erro ao validar a conta ("+ nroConta +"): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao validar a conta ("+ nroConta +"): " + e);
			}
		}
		return result;
	}
	
	public Double valida_saldo(String nroconta) {
		Double saldo = null;
		String sql = "select saldo from conta where nroconta = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nroconta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				saldo = rs.getDouble("saldo");
			}
		} catch (SQLException e) {
			log.error("Erro ao validar o saldo da conta ("+ nroconta +"): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao validar o saldo da conta ("+ nroconta +"): " + e);
			}
		}
		return saldo;
	}
	
	public boolean valida_senha(String nroConta, String p_senha) {
		boolean result = false;
		String senha = null;
		String sql = "select senha from conta where nroconta = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nroConta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				senha = rs.getString("senha");
			}
			
			if (senha.equals(p_senha)) {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Erro ao validar a senha da conta ("+ nroConta +"): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao validar a senha da conta ("+ nroConta +"): " + e);
			}
		}
		return result;
	}
	
	public boolean atualiza_saldo(String nroconta, Double valor) {
		boolean result = false;
		String sql = "update conta set saldo = ? where nroconta = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, Integer.parseInt(nroconta));
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao atualizar o saldo da conta ("+ nroconta +"): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao atualizar o saldo da conta ("+ nroconta +"): " + e);
			}
		}
		return result;
	}
}
