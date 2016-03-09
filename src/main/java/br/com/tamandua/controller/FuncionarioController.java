package br.com.tamandua.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.tamandua.funcionario.entities.FuncionarioEntity;
import br.com.tamandua.funcionario.service.FuncionarioService;

@Path("/funcionario")
public class FuncionarioController {

	@Inject
	private FuncionarioService funcionarioService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response todosFuncionarios() {
		List<FuncionarioEntity> lista = funcionarioService
				.buscarTodosFuncionarios();
		Response resp = null;
		if (lista.size() > 0) {
			resp = Response.status(200)
					.header("Content-type", "application/json").entity(lista)
					.build();
		} else {
			resp = Response.status(204)
					.header("Content-type", "application/json").build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{cpf}")
	public Response buscarFuncionario(@PathParam("cpf") String cpf) {
		List<FuncionarioEntity> lista = funcionarioService
				.bucarFuncionario(cpf);
		Response resp = null;
		if (lista.size() > 0) {
			resp = Response.status(200)
					.header("Content-type", "application/json").entity(lista)
					.build();
		} else {
			resp = Response.status(204)
					.header("Content-type", "application/json").build();
		}
		return resp;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastroFuncionario(@FormParam("cpf") String cpf,
			@FormParam("nome") String nome, @FormParam("senha") String senha) {
		Response resp = null;

		String result = funcionarioService.inserirFuncionario(cpf, nome, senha);
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(201).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarFuncionario(@FormParam("cpf") String cpf,
			@FormParam("nome") String nome, @FormParam("senha") String senha) {
		Response resp = null;

		String result = funcionarioService.editarFuncionario(cpf, nome, senha);
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(200).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}
}
