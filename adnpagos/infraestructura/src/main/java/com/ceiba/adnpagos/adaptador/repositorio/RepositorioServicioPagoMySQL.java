package com.ceiba.adnpagos.adaptador.repositorio;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class RepositorioServicioPagoMySQL  implements RepositorioPago{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "pago", value = "crear")
	private static String sqlCrear;
	
	
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
	public void elimnar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void existePorId(Long id) {
		// TODO Auto-generated method stub
		
	}

}
