package com.ceiba.adnpagos.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioServicioElectricoMySQL implements RepositorioServicioElectrico {
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace="electrico", value="crear")
	private static String sqlCrear;
	
	
	@SqlStatement(namespace="electrico", value="actualizar")
	private static String sqlActualizar;
	
	@SqlStatement (namespace = "electrico", value="eliminar")
	private static String sqlEliminar;
	
	public RepositorioServicioElectricoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate=customNamedParameterJdbcTemplate;
	
	}

	@Override
	public Long crear(ServicioElectrico servicio) {
		return this.customNamedParameterJdbcTemplate.crear(servicio, sqlCrear);
	}

	@Override
	public void actualizar(ServicioElectrico servicio) {
		this.customNamedParameterJdbcTemplate.actualizar(servicio, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);	
	}

	@Override
	public boolean existe(String numeroServicio) {
		// TODO Auto-generated method stub
		return false;
	}

}
