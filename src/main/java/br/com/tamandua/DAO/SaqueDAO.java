package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.SaqueModel;

public class SaqueDAO extends DAO {
	
	public boolean inserir_saque(SaqueModel saque) {
		boolean result = false;
		String sql = "insert into saque (codigo, valor, nroconta, codigo_funcionario) values (?,?,?,?)";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, saque.getCodigo());
			stmt.setDouble(2, saque.getValor());
			stmt.setInt(3, saque.getNroconta());
			stmt.setString(4, saque.getCodfuncionario());
			stmt.execute();
		} catch (SQLException e) {
			log.error("Erro ao inserir o saque " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao inserir o saque " + e);
			}
		}
		return result;
	}

}
