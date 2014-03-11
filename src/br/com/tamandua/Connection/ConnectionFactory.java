package br.com.tamandua.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost/tamandua", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
