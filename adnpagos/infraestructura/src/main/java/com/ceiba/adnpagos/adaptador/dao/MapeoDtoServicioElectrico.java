package com.ceiba.adnpagos.adaptador.dao;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoDtoServicioElectrico implements RowMapper<DtoServicioElectrico> {

	@Override
	public DtoServicioElectrico mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id= rs.getLong("id");
		String numeroServicio= rs.getString("numero_servicio");
		String identificacionCliente= rs.getString("identificacion_cliente");
		String nombreCliente=rs.getString("nombre_cliente");
		String mesPago= rs.getString("mes_pago");
		LocalDateTime fechaMaximaPago= rs.getTimestamp("fecha_maxima_pago").toLocalDateTime();
		Double valor= rs.getDouble("valor");
		boolean estado=rs.getBoolean("estado");
		
		return new DtoServicioElectrico(id, numeroServicio, identificacionCliente, nombreCliente, mesPago, fechaMaximaPago, valor, estado);
	}

}
