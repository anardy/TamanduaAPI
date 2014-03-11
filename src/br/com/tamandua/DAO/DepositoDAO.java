package br.com.tamandua.DAO;

import java.sql.SQLException;

import br.com.tamandua.Model.DepositoModel;

import com.mysql.jdbc.PreparedStatement;

public class DepositoDAO extends DAO {
	
	public boolean inserir_deposito(DepositoModel deposito) {
		boolean result = false;
		String sql = "insert into deposito (codigo, valor, nroconta, codigo_funcionario, tipo) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, deposito.getCodigo());
			stmt.setDouble(2, deposito.getValor());
			stmt.setInt(3, deposito.getNroconta());
			stmt.setString(4, deposito.getCodigo_funcionario());
			stmt.setString(5, deposito.getTipo());
			stmt.execute();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
