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

import br.com.tamandua.transacao.entities.ContaEntity;
import br.com.tamandua.transacao.entities.HistTransacaoEntity;
import br.com.tamandua.transacao.service.TransacaoService;

@Path("/transacao")
public class TransacaoController {

	@Inject
	private TransacaoService transacaoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saldo/{nroconta}")
	public Response saldo(@PathParam("nroconta") Integer nroconta) {
		List<ContaEntity> lista = transacaoService.buscarConta(nroconta);
		Response resp = null;
		if (lista.size() > 0) {
			resp = Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.header("Content-type", "application/json")
				.entity(lista).build();
		} else {
			resp = Response.status(204).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.header("Content-type", "application/json").build();
		}
		return resp;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/transferencia")
	public Response transferecencia(
			@FormParam("nrocontaconcedente") Integer nrocontaConcedente,
			@FormParam("senhaconcedente") String senhaConcedente,
			@FormParam("valor") Double valor,
			@FormParam("nrocontabeneficiado") Integer nrocontaBeneficiado) {

		Response resp = null;
		
		String result = transacaoService.inserirTransferencia(nrocontaConcedente,senhaConcedente,valor,nrocontaBeneficiado);

		HashMap<String, String> entity = new HashMap<String, String>();
        entity.put("msg", result);
		
		if (result.equals("OK")) {
			entity.put("tipo", "ok");
			resp = Response.status(200)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST")
					.header("Content-type", "application/json").entity(entity).build();
		} else {
			entity.put("tipo", "erro");
			resp = Response.status(203).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST")
					.header("Content-type", "application/json").entity(entity).build();
		}
		return resp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/extrato/{nroconta}")
	public Response extrato(@PathParam("nroconta") Integer nroConta) {
		List<HistTransacaoEntity> lista = transacaoService.extrairExtrato(nroConta);
		Response resp = null;
		if (lista.size() > 0) {
			resp = Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.header("Content-type", "application/json")
				.entity(lista).build();
		} else {
			resp = Response.status(204).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.header("Content-type", "application/json").build();
		}
		return resp;
	}
	

}
