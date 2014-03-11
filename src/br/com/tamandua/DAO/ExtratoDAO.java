package br.com.tamandua.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.tamandua.Model.ExtratoModel;
import com.mysql.jdbc.PreparedStatement;

public class ExtratoDAO extends DAO {
	
	public List<ExtratoModel> extrair_extrato_deposito(String nroconta, String data_inicio, String data_final) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data_inicio_nova = null;
		Date data_final_nova = null;
		try {
			data_inicio_nova = new java.sql.Date(format.parse(data_inicio).getTime());
			data_final_nova = new java.sql.Date(format.parse(data_final).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select d.codigo,d.valor,d.data,d.codigo_funcionario,d.tipo from deposito d where d.nroconta = ? AND d.data BETWEEN ? AND ?";
		List<ExtratoModel> dados = new ArrayList<ExtratoModel>();
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(nroconta));
			stmt.setDate(2, data_inicio_nova);
			stmt.setDate(3, data_final_nova);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ExtratoModel extrato = new ExtratoModel();
				extrato.setCodigo(rs.getString("codigo"));
				extrato.setValor(rs.getDouble("valor"));
				extrato.setData(rs.getString("data"));
				extrato.setCodigo_funcionario(rs.getString("codigo_funcionario"));
				extrato.setTipo(rs.getString("tipo"));	
				dados.add(extrato);
			}
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dados;
	}
	
	public List<ExtratoModel> extrair_extrato_saque(String nroconta) {
		String sql = "select d.codigo,d.valor,d.data,d.codigo_funcionario from saque d where d.nroconta = ?";
		List<ExtratoModel> dados = new ArrayList<ExtratoModel>();
		try {
			PreparedStatement stmt = (PreparedStatement) getConnetion()
					.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(nroconta));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ExtratoModel extrato = new ExtratoModel();
				extrato.setCodigo(rs.getString("codigo"));
				extrato.setValor(rs.getDouble("valor"));
				extrato.setData(rs.getString("data"));
				extrato.setCodigo_funcionario(rs.getString("codigo_funcionario"));
				dados.add(extrato);
			}
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dados;
	}
}
