package br.com.tamandua.resource;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.tamandua.controleacesso.entities.AcessoEntity;
import br.com.tamandua.controleacesso.entities.ControleAcessoEntity;
import br.com.tamandua.controleacesso.entities.DesassociarEntity;
import br.com.tamandua.controleacesso.entities.StatusEntity;
import br.com.tamandua.controleacesso.entities.TipoAcessoEntity;
import br.com.tamandua.controleacesso.service.ControleAcessoService;

@Path("/ctracesso")
public class ControleAcessoResource {
	
	@EJB
	private ControleAcessoService controleAcessoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response todosControleAcesso() {
		List<ControleAcessoEntity> lista = controleAcessoService
				.buscarControlesAcessos();
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
	public Response controleAcesso(@PathParam("cpf") String cpf) {
		List<ControleAcessoEntity> lista = controleAcessoService
				.buscarControleAcesso(cpf);
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

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarControleacesso(@FormParam("cpf") String cpf,
			@FormParam("status") Integer status, @FormParam("tipo") Integer tipo) {
		Response resp = null;

		AcessoEntity acesso = new AcessoEntity(cpf, status, tipo);
		String result = "Acesso alterado com sucesso";
		try {
			controleAcessoService.editarAcesso(acesso);
		} catch (Exception e) {
			result = e.getMessage();
		}
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(200).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/desassociados")
	public Response todosDesassociados() {
		List<DesassociarEntity> lista = controleAcessoService
				.buscarDesassociados();
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
	@Path("/desassociado/{cpf}")
	public Response todosDesassociados(@PathParam("cpf") String cpf) {
		List<DesassociarEntity> lista = controleAcessoService
				.buscarDesassociado(cpf);
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
	@Path("/tipoacesso")
	public Response todosTipoAcesso() {
		List<TipoAcessoEntity> lista = controleAcessoService
				.buscarTodosTipoAcesso();
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
	@Path("/status")
	public Response todosStatus() {
		List<StatusEntity> lista = controleAcessoService.buscarTodosStatus();
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
	@Path("/associar")
	public Response cadastroAcesso(@FormParam("cpf") String cpf,
			@FormParam("status") Integer status, @FormParam("tipo") Integer tipo) {
		Response resp = null;

		AcessoEntity acesso = new AcessoEntity(cpf, status, tipo);
		String result = "Acesso inserido com sucesso";
		try {
			controleAcessoService.inserirAcesso(acesso);
		} catch (Exception e) {
			result = e.getMessage();
		}
		HashMap<String, String> entity = new HashMap<String, String>();
		entity.put("msg", result);

		resp = Response.status(201).header("Content-type", "application/json")
				.entity(entity).build();

		return resp;
	}
}