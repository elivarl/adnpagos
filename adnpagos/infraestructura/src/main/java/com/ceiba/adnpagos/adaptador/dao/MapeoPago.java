package com.ceiba.adnpagos.adaptador.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoPago implements RowMapper<DtoPago>, MapperResult {

	@Override
	public DtoPago mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id=rs.getLong("id");
		LocalDateTime fechaPago= extraerLocalDateTime(rs, "fecha_pago");
		String identificacionCliente = rs.getString("identificacion_cliente");
		Double valorTotal= rs.getDouble("total");
		
		return new DtoPago(id, fechaPago, identificacionCliente, valorTotal);
	}

}
