package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

public interface RepositorioServicioElectrico {
	Long crear(ServicioElectrico servicio);
	void actualizar(ServicioElectrico servicio);
	void eliminar (Long id);
	boolean existe (String numeroServicio);
	boolean existePorId(Long id);

}
