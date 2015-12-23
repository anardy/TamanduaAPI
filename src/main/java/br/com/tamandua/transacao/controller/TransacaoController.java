package br.com.tamandua.transacao.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/transacao")
public class TransacaoController {

	@GET
	@Path("/saque")
	public String saque() {
		return "Operacao de Saque";
	}
}
