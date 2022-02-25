package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.Servicio;

public interface RepositorioServicio {
	Long crear(Servicio servicio);
	void actualizar(Servicio servicio);
	void eliminar (Servicio servicio);
	boolean existe (String numeroServicio);

}
