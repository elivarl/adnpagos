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
	
	@SqlStatement(namespace="electrico", value="existe")
	private static String sqlExiste;
	
	@SqlStatement(namespace="electrico", value="existePorId")
	private static String sqlExistePorId; 
	
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
		MapSqlParameterSource paramSource= new MapSqlParameterSource();
		paramSource.addValue("numero_servicio", numeroServicio);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste, paramSource, Boolean.class);
	}

	@Override
	public boolean existePorId(Long id) {
		MapSqlParameterSource paramSource= new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId, paramSource, Boolean.class);
	}

}
