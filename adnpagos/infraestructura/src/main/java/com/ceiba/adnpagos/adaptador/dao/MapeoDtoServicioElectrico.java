package com.ceiba.adnpagos.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.adnpagos.modelo.dto.*;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoDtoServicioElectrico implements RowMapper<DtoServicioElectrico>, MapperResult {

	@Override
	public DtoServicioElectrico mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id= rs.getLong("id");
		String numeroServicio= rs.getString("numero_servicio");
		String identificacionCliente= rs.getString("identificacion_cliente");
		String nombreCliente=rs.getString("nombre_cliente");
		String mesPago= rs.getString("mes_pago");
		LocalDateTime fechaMaximaPago= extraerLocalDateTime(rs,"fecha_maxima_pago");
		Double valor= rs.getDouble("valor");
		boolean estado=rs.getBoolean("estado");
		LocalDateTime fechaCreacion= extraerLocalDateTime(rs, "fecha_creacion");
		
		return new DtoServicioElectrico(id, numeroServicio, identificacionCliente, nombreCliente, mesPago, fechaMaximaPago, valor, estado, fechaCreacion);
	}

}
