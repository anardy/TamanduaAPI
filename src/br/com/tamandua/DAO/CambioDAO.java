package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.CambioModel;

public class CambioDAO extends DAO {

	public boolean inserir_cambio(CambioModel cambio) {
		boolean result = false;
		String sql = "insert into cambio (codigo, tipo, valor_tamandua, valor_real, codigo_funcionario) values (?,?,?,?,?)";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cambio.getCodigo());
			stmt.setString(2, cambio.getTipo());
			stmt.setDouble(3, cambio.getValor_tamandua());
			stmt.setDouble(4, cambio.getValor_real());
			stmt.setString(5, cambio.getCodigo_funcionario());
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao inserir o cambio: " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao inserir o cambio: " + e);
			}
		}
		return result;
	}

}
