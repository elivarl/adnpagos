package com.ceiba.adnpagos.puerto.dao;

import java.util.List;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

public interface DaoServicioElectrico {
	public List<DtoServicioElectrico> listar();
	public List<ServicioElectrico> listarServicio();

}
