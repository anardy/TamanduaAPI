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

import br.com.tamandua.correntista.entities.CorrentistaEntity;
import br.com.tamandua.correntista.service.CorrentistaService;

@Path("/correntista")
public class CorrentistaController {
	@Inject
	private CorrentistaService correntistaService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response todosCorrentistas() {
		List<CorrentistaEntity> lista = correntistaService
				.buscarTodosCorrentistas();
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
	public Response buscarCorrentista(@PathParam("cpf") String cpf) {
		List<CorrentistaEntity> lista = correntistaService
				.buscarCorrentista(cpf);
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
	public Response inserirCorrentista(@FormParam("cpf") String cpf,
			@FormParam("nome") String nome,
			@FormParam("endereco") String endereco,
			@FormParam("telefone") String telefone) {
		Response resp = null;

		String result = correntistaService.inserirCorrentista(cpf, nome,
				endereco, telefone);
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(201).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarCorrentista(@FormParam("cpf") String cpf,
			@FormParam("nome") String nome,
			@FormParam("endereco") String endereco,
			@FormParam("telefone") String telefone) {
		Response resp = null;

		String result = correntistaService.editarCorrentista(cpf, nome,
				endereco, telefone);
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(200).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}

}
