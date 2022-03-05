package com.ceiba.adnpagos.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoServicioElectricoMySQL implements DaoServicioElectrico{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "electrico", value = "listar")
	private static String sqlListar;

	@SqlStatement(namespace = "electrico", value = "obtenerPorId")
	private static  String sqlObtenerPorId;
	
	public DaoServicioElectricoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate=customNamedParameterJdbcTemplate;
	}

	@Override
	public List<DtoServicioElectrico> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoListarDtoServicioElectrico());
	}

	@Override
	public DtoServicioElectrico obtenerPorId(Long id) {
		MapSqlParameterSource paramSource= new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId, paramSource, new MapeoDtoServicioElectrico());
	}

}
