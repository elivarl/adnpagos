package com.ceiba.adnpagos.adaptador.repositorio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class RepositorioServicioPagoMySQL  implements RepositorioPago{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "pago", value = "crear")
	private static String sqlCrear;
	
	@SqlStatement(namespace = "pago", value = "actualizar")
	private static String sqlActualizar;
	
	@SqlStatement (namespace = "pago", value="crearpd")
	private static String sqlCrearPagoDetalle;

	@SqlStatement(namespace="electrico", value="actualizar")
	private static String sqlActualizarServicioElectrico;
	
	
	public RepositorioServicioPagoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate=customNamedParameterJdbcTemplate;
	}
	@Override
	public Long crear(Pago pago) {
		return this.customNamedParameterJdbcTemplate.crear(pago, sqlCrear);
	}

	@Override
	public void actualizar(Pago pago) {
		this.customNamedParameterJdbcTemplate.actualizar(pago, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		
	}

	@Override
	public boolean existePorId(Long id) {
		return false;
	}
	@Override
	public Long crearPagoDetalle(PagoDetalle pagoDetalle) {
		return this.customNamedParameterJdbcTemplate.crear(pagoDetalle, sqlCrearPagoDetalle);
	}

	@Override
	public void actualizarServivioElectrico(ServicioElectrico servicio) {
		this.customNamedParameterJdbcTemplate.actualizar(servicio, sqlActualizarServicioElectrico);

	}

}
