package br.com.tamandua.Controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tamandua.DAO.ClienteDAO;
import br.com.tamandua.Model.ClienteModel;

public class Cliente {

	public String inserirCliente(String cpf, String nome, String endereco,
			String telefone, short status) {
		String mensagem = "";
		boolean result = false;

		ClienteModel cliente = new ClienteModel();

		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setTelefone(telefone);
		cliente.setStatus(status);

		result = new ClienteDAO().inserirCliente(cliente);
		mensagem = (result) ? "Cliente cadastrado com Sucesso!"
				: "Houve um erro ao cadastrar o cliente";
		return mensagem;
	}
	
	public String consultarCliente(String cpf) {
		String txt = "";
		List<ClienteModel> dados = new ArrayList<ClienteModel>();
		ClienteDAO clientedao = new ClienteDAO();
		dados = clientedao.consultarCliente(cpf);
		for (ClienteModel clienteModel : dados) {
			txt += clienteModel.getCpf() + ";" + clienteModel.getNome() + ";" + clienteModel.getEndereco()
					+ ";" + clienteModel.getTelefone() + ";" + clienteModel.getStatus();
		}
		return txt;
	}
	
	public String removerCliente(String cpf) {
		String mensagem = "";
		ClienteDAO clientedao = new ClienteDAO();
		boolean result = clientedao.removerCliente(cpf);
		mensagem = (result) ? "Cliente removido com Sucesso!"
				: "Houve um erro ao remover o cliente";
		return mensagem;
	}
	
	public String editarCliente(String cpf, String nome, String endereco,
			String telefone, short status) {
		String mensagem = "";
		ClienteDAO clientedao = new ClienteDAO();
		
		ClienteModel cliente = new ClienteModel();

		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setTelefone(telefone);
		cliente.setStatus(status);
		
		boolean result = clientedao.editarCliente(cliente);
		mensagem = (result) ? "Cliente atualizado com Sucesso!"
				: "Houve um erro ao atualizar o cliente";
		return mensagem;
	}
}
