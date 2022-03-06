package com.ceiba.adnpagos.adaptador.dao;

import java.util.List;

import com.ceiba.adnpagos.puerto.dao.DaoPago;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoPagoMySQL implements DaoPago {
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement (namespace = "pago", value = "listar")
	private static String sqlListar; 
	
	
	public DaoPagoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate=customNamedParameterJdbcTemplate;
	}

	@Override
	public List<DtoPago> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoPago());
	}

}
