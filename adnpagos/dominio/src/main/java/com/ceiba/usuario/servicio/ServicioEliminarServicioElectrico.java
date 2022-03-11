package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioEliminarServicioElectrico {
	private static final String ID_SERVICIO_NO_EXISTE = "El id del servicio no existe";

	private final RepositorioServicioElectrico repositorioServicioElectrico;
	
	public ServicioEliminarServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico=repositorioServicioElectrico;
	}


	
	public void ejecutar (Long id) {
		//validarExistenciaPreviaPorId(id);
		this.repositorioServicioElectrico.eliminar(id);
	}

	private void validarExistenciaPreviaPorId(Long id) {
		boolean existe = repositorioServicioElectrico.existePorId(id);
		if(!existe) {
			throw new ExcepcionDuplicidad(ID_SERVICIO_NO_EXISTE);
		}
	}

}
