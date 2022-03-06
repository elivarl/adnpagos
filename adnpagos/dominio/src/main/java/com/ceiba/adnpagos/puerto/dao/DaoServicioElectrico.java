package com.ceiba.adnpagos.puerto.dao;

import java.util.List;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

public interface DaoServicioElectrico {
	/**
	 * Permite listar usuarios
	 * @return los usuarios
	 */
	public List<DtoServicioElectrico> listar();
	/**
	 * Permite buscar un servicio electrico
	 * @return un Servicio Electrico
	 */
	public DtoServicioElectrico obtenerPorId(Long id);

}
