package br.com.tamandua.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tamandua.Connection.ConnectionFactory;
import br.com.tamandua.Model.FuncionarioModel;

public class FuncionarioDAO extends DAO {
	
	public boolean inserirFuncionario(FuncionarioModel funcionario) {
		boolean result = false;
		String sql = "insert into funcionario (cpf, nome, login, senha, status, funcao) values (?,?,?,?,?,?)";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getCpf());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getLogin());
			stmt.setString(4, funcionario.getSenha());
			stmt.setShort(5, funcionario.getStatus());
			stmt.setString(6, funcionario.getFuncao());
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao inserir o funcionario: " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao inserir o funcionario: " + e);
			}
		}
		return result;
	}

	public boolean editarFuncionario(FuncionarioModel funcionario) {
		boolean result = false;
		String sql = "update funcionario f set f.nome = ?, login = ?, senha = ?, status = ?, funcao = ? where f.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getLogin());
			stmt.setString(3, funcionario.getSenha());
			stmt.setShort(4, funcionario.getStatus());
			stmt.setString(5, funcionario.getFuncao());
			stmt.setString(6, funcionario.getCpf());
			stmt.execute();
			result = true;
			log.error("Infos: " + funcionario.getCpf());
		} catch (SQLException e) {
			log.error("Erro ao atualizar os dados do funcionario (" + funcionario.getCpf() + "): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao atualizar os dados do funcionario (" + funcionario.getCpf() + "): " + e);
			}
		}
		return result;
	}

	public boolean removerFuncionario(String cpf) {
		boolean result = false;
		String sql = "update funcionario f set f.status = ? where f.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, DESATIVO);
			stmt.setString(2, cpf);
			stmt.execute();
			result = true;
		} catch (SQLException e) {
			log.error("Erro ao remover o funcionario (" + cpf + "): " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Erro ao remover o funcionario (" + cpf + "): " + e);
			}
		}
		return result;
	}

	public List<FuncionarioModel> consultarFuncionario(String cpf) {
		String sql = "select f.nome, f.login, f.status, f.funcao from funcionario f where f.cpf = ?";
		Connection connection = ConnectionFactory.getConnection();
		List<FuncionarioModel> dados = new ArrayList<FuncionarioModel>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				FuncionarioModel funcionarioModel = new FuncionarioModel();
				funcionarioModel.setCpf(cpf);
				funcionarioModel.setNome(rs.getString("nome"));
				funcionarioModel.setLogin(rs.getString("login"));
				funcionarioModel.setStatus(rs.getShort("status"));
				funcionarioModel.setFuncao(rs.getString("funcao"));
				dados.add(funcionarioModel);
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
