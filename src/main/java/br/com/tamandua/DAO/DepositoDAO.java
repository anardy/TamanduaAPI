package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.DepositoModel;

public class DepositoDAO extends DAO {
	
	public boolean inserir_deposito(DepositoModel deposito) {
		boolean result = false;
		String sql = "insert into deposito (codigo, valor, nroconta, codigo_funcionario, tipo) values (?,?,?,?,?)";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, deposito.getCodigo());
			stmt.setDouble(2, deposito.getValor());
			stmt.setInt(3, deposito.getNroconta());
			stmt.setString(4, deposito.getCodigo_funcionario());
			stmt.setString(5, deposito.getTipo());
			stmt.execute();
		} catch (SQLException e) {
			log.error("Erro ao inserir deposito " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao inserir deposito " + e);
			}
		}
		return result;
	}

}
