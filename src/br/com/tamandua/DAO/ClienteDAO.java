package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.ClienteModel;

public class ClienteDAO extends DAO {

	public boolean inserirCliente(ClienteModel cliente) {
		boolean result = false;
		String sql = "insert into cliente (cpf, nome, endereco, telefone, status) values (?,?,?,?,?)";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());
			stmt.setShort(5, cliente.getStatus());
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao inserir o cliente: " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao inserir o cliente: " + e);
			}
		}
		return result;
	}

	public boolean editarCliente(ClienteModel cliente) {
		boolean result = false;
		String sql = "update cliente c set c.nome = ?, c.endereco = ?, c.telefone = ?, c.status = ? where c.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getTelefone());
			stmt.setShort(4, cliente.getStatus());
			stmt.setString(5, cliente.getCpf());
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao atualizar os dados do cliente (" + cliente.getCpf() + "): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao atualizar os dados do cliente (" + cliente.getCpf() + "): " + e);
			}
		}
		return result;
	}

	public boolean removerCliente(String cpf) {
		boolean result = false;
		String sql = "update cliente c set c.status = ? where c.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, DESATIVO);
			stmt.setString(2, cpf);
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao remover o cliente (" + cpf + "): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao remover o cliente (" + cpf + "): " + e);
			}
		}
		return result;
	}

	public List<ClienteModel> consultarCliente(String cpf) {
		String sql = "select c.nome, c.endereco, c.telefone, c.status from cliente c where c.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		List<ClienteModel> dados = new ArrayList<ClienteModel>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ClienteModel cliente = new ClienteModel();
				cliente.setCpf(cpf);
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setStatus(rs.getShort("status"));
				dados.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dados;
	}

}
