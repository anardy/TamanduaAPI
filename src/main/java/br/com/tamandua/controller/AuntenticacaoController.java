package br.com.tamandua.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.tamandua.autenticacao.entities.MenuFuncionarioEntity;
import br.com.tamandua.autenticacao.service.AutenticaoService;
import br.com.tamandua.transacao.service.TransacaoService;

@Path("/autentica")
public class AuntenticacaoController {

	@Inject
	private AutenticaoService autenticaService;
	
	@Inject
	private TransacaoService transacaoService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response tipoAcesso(@FormParam("cpf") String cpf) {

		Response resp = null;

		String result = autenticaService.tipoAcesso(cpf);

		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		if (result.equals("CPF não existe")) {
			entity.put("tipo", "erro");
			resp = Response.status(401)
					.header("Content-type", "application/json").entity(entity)
					.build();
		} else {
			entity.put("tipo", "ok");
			resp = Response.status(200)
					.header("Content-type", "application/json").entity(entity)
					.build();
		}
		return resp;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cliente")
	public Response autenticaCliente(@FormParam("nroconta") Integer nroConta,
			@FormParam("senha") String senha) {

		Response resp = null;

		String result = transacaoService.autentica(nroConta, senha);

		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		if (result.equals("Cliente autenticado")) {
			entity.put("tipo", "ok");
			resp = Response.status(200)
					.header("Content-type", "application/json").entity(entity)
					.build();
		} else {
			entity.put("tipo", "erro");
			resp = Response.status(401)
					.header("Content-type", "application/json").entity(entity)
					.build();
		}
		return resp;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/funcionario")
	public Response autenticaFuncionario(@FormParam("cpf") String cpf,
			@FormParam("senha") String senha) {

		Response resp = null;

		String result = autenticaService.autenticaFuncionario(cpf, senha);
		
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		if (result.equals("Funcionario autenticado")) {
			entity.put("tipo", "ok");
			resp = Response.status(200)
					.header("Content-type", "application/json").entity(entity)
					.build();
		} else {
			entity.put("tipo", "erro");
			resp = Response.status(401)
					.header("Content-type", "application/json").entity(entity)
					.build();
		}
		return resp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/menuFuncionario/{tipo}")
	public Response menuFuncionario(@PathParam("tipo") String tipo) {
		List<MenuFuncionarioEntity> lista = autenticaService.montarMenuFuncionario(tipo);
		Response resp = null;
		if (lista.size() > 0) {
			resp = Response.status(200)
				.header("Content-type", "application/json")
				.entity(lista).build();
		} else {
			resp = Response.status(204)
					.header("Content-type", "application/json").build();
		}
		return resp;
	}
	
}