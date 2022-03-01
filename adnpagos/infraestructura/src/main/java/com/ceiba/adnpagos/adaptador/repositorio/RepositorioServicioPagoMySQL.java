package com.ceiba.adnpagos.adaptador.repositorio;

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
	
	@SqlStatement (namespace = "pago", value="crearpd")
	private static String sqlCrearPagoDetalle;
	
	
	public RepositorioServicioPagoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate=customNamedParameterJdbcTemplate;
	}
	@Override
	public Long crear(Pago pago) {
		return this.customNamedParameterJdbcTemplate.crear(pago, sqlCrear);
	}

	@Override
	public void actualizar(Pago pago) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existePorId(Long id) {
		return false;
	}
	@Override
	public void crearPagoDetalle(PagoDetalle pagoDetalle) {
		this.customNamedParameterJdbcTemplate.crear(pagoDetalle, sqlCrearPagoDetalle);
	}

}
