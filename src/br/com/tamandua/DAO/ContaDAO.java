package br.com.tamandua.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ContaDAO extends DAO {
	
	public boolean valida_nroconta(String nroConta) {
		boolean result = false;
		String sql = "select * from conta where nroconta = ?";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, nroConta);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Double valida_saldo(String nroconta) {
		Double saldo = null;
		String sql = "select saldo from conta where nroconta = ?";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, nroconta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				saldo = rs.getDouble("saldo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saldo;
	}
	
	public boolean valida_senha(String nroConta, String p_senha) {
		boolean result = false;
		String senha = null;
		String sql = "select senha from conta where nroconta = ?";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, nroConta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				senha = rs.getString("senha");
			}
			
			if (senha.equals(p_senha)) {
				result = true;
			}
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean atualiza_saldo(String nroconta, Double valor) {
		boolean result = false;
		try {
			String sql = "update conta set saldo = ? where nroconta = ?";
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, Integer.parseInt(nroconta));
			stmt.execute();
			result = true;
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
