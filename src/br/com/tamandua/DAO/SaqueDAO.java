package br.com.tamandua.DAO;

import java.sql.SQLException;

import br.com.tamandua.Model.SaqueModel;

import com.mysql.jdbc.PreparedStatement;

public class SaqueDAO extends DAO {
	
	public boolean inserir_saque(SaqueModel saque) {
		boolean result = false;
		String sql = "insert into saque (codigo, valor, nroconta, codigo_funcionario) values (?,?,?,?)";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, saque.getCodigo());
			stmt.setDouble(2, saque.getValor());
			stmt.setInt(3, saque.getNroconta());
			stmt.setString(4, saque.getCodfuncionario());
			stmt.execute();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
