package br.com.tamandua.Controller;

import br.com.tamandua.DAO.CambioDAO;
import br.com.tamandua.Model.CambioModel;

public class Cambio extends Transaction {

	public String trans_cambio(String tipo, Double valor_tamandua, Double valor_real, String codigoFunc) {
		String mensagem = "";
		boolean result = false;

		if ((valor_real > 0) && (valor_tamandua > 0)) {
			CambioModel cambio = new CambioModel();
			cambio.setCodigo(getToken());

			cambio.setTipo(tipo);
			cambio.setValor_tamandua(valor_tamandua);
			cambio.setValor_real(valor_real);
			cambio.setCodigo_funcionario(codigoFunc);

			result = new CambioDAO().inserir_cambio(cambio);
			mensagem = (result) ? "Transação de Cambio executada com sucesso" : "Houve um erro na execução da transação de Cambio";
		} else {
			mensagem = "Valores incorretos";
		}
		return mensagem;
	}
}
