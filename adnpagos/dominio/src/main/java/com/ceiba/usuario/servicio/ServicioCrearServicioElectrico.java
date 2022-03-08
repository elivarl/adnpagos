package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearServicioElectrico {
	private static final String SERVICIO_POR_ID_YA_EXISTE = "El id del  del servicio ya existe";
	private static final String SERVICIO_POR_NUMEO_YA_EXISTE = "El numero del  del servicio ya existe";

	private final RepositorioServicioElectrico repositorioServicioElectrico;

	public ServicioCrearServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico = repositorioServicioElectrico;
	}

	public Long ejecutar(ServicioElectrico servicioElectrico) {
		validarExistenciaPreviaPorId(servicioElectrico);
		validarExistenciaPreviaPorNumeroServicio(servicioElectrico);
		return this.repositorioServicioElectrico.crear(servicioElectrico);
	}
	
	private void validarExistenciaPreviaPorId(ServicioElectrico servicioElectrico) {
		boolean existe = repositorioServicioElectrico.existePorId(servicioElectrico.getId());
		if(existe) {
			throw new ExcepcionDuplicidad(SERVICIO_POR_ID_YA_EXISTE);
		}
	}
	private void validarExistenciaPreviaPorNumeroServicio(ServicioElectrico servicioElectrico) {
		boolean existe = repositorioServicioElectrico.existe(servicioElectrico.getNumeroServicio());
		if(existe) {
			throw new ExcepcionDuplicidad(SERVICIO_POR_NUMEO_YA_EXISTE);
		}
	}
}
