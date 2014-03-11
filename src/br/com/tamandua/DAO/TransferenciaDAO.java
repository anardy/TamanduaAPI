package br.com.tamandua.DAO;

import java.sql.SQLException;

import br.com.tamandua.Model.TransferenciaModel;

import com.mysql.jdbc.PreparedStatement;

public class TransferenciaDAO extends DAO {
	
	public boolean inserir_transferencia(TransferenciaModel transferencia) {
		boolean result = false;
		String sql = "insert into transferencia (codigo, valor, cliente_concedente, cliente_beneficiado, codigo_funcionario) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, transferencia.getCodigo());
			stmt.setDouble(2, transferencia.getValor());
			stmt.setInt(3, transferencia.getCliente_concedente());
			stmt.setInt(4, transferencia.getCliente_beneficiado());
			stmt.setString(5, transferencia.getCodigo_funcionario());
			stmt.execute();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
