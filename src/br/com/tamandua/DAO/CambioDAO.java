package br.com.tamandua.DAO;

import java.sql.SQLException;

import br.com.tamandua.Model.CambioModel;

import com.mysql.jdbc.PreparedStatement;

public class CambioDAO extends DAO {

	public boolean inserir_cambio(CambioModel cambio) {
		boolean result = false;
		String sql = "insert into cambio (codigo, tipo, valor_tamandua, valor_real, codigo_funcionario) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setString(1, cambio.getCodigo());
			stmt.setString(2, cambio.getTipo());
			stmt.setDouble(3, cambio.getValor_tamandua());
			stmt.setDouble(4, cambio.getValor_real());
			stmt.setString(5, cambio.getCodigo_funcionario());
			stmt.execute();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
