package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearServicioElectrico {
	private static final String SERVICIO_YA_EXISTE = "El número de servicio ya existe";

	private final RepositorioServicioElectrico repositorioServicioElectrico;

	public ServicioCrearServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico = repositorioServicioElectrico;
	}

	public Long ejecutar(ServicioElectrico servicioElectrico) {
		validarExistenciaPrevia(servicioElectrico);
		return this.repositorioServicioElectrico.crear(servicioElectrico);
	}
	
	private void validarExistenciaPrevia(ServicioElectrico servicioElectrico) {
		boolean existe = repositorioServicioElectrico.existe(servicioElectrico.getNumeroServicio());
		if(existe) {
			throw new ExcepcionDuplicidad(SERVICIO_YA_EXISTE);
		}
	}
}
