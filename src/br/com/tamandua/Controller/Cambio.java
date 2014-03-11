package br.com.tamandua.Controller;

import br.com.tamandua.DAO.CambioDAO;
import br.com.tamandua.Model.CambioModel;

public class Cambio extends Transaction {

	public String trans_cambio(String tipo, Double valor_tamandua, Double valor_real) {
		String result = "";

		if ((valor_real > 0) && (valor_tamandua > 0)) {
			CambioModel cambio = new CambioModel();
			cambio.setCodigo(getToken());

			cambio.setTipo(tipo);
			cambio.setValor_tamandua(valor_tamandua);
			cambio.setValor_real(valor_real);
			cambio.setCodigo_funcionario("321");

			CambioDAO cambiodao = new CambioDAO();
			cambiodao.inserir_cambio(cambio);
			result = "Transação de Cambio executada com sucesso";
		} else {
			result = "Valores incorretos";
		}
		return result;
	}
}
