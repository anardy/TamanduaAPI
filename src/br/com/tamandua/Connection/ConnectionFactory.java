package br.com.tamandua.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	private static DataSource dataSource = null;
	
	public ConnectionFactory() {
		
	}
	
	public static DataSource getDataSource() {
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:/tamanduaDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	public static Connection getConnection() {
		try {
			return ConnectionFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
