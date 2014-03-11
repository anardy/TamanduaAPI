package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.tamandua.Connection.ConnectionFactory;

public class DAO {
	private Connection connection;
	
	public Connection getConnetion() {
		this.connection = new ConnectionFactory().getConnection();
		return connection;
	}
	
	public void fecharConexao() throws SQLException {
		getConnetion().close();
	}
}
