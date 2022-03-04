package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioActualizarServicioElectrico {
	private static final String SERVICIO_NO_EXISTE = "El numero de servicio no existe";
	
	private final RepositorioServicioElectrico repositorioServicioElectrico;
	
	public ServicioActualizarServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico=repositorioServicioElectrico;
	}
	
	public void ejeuctar (ServicioElectrico servicioElectrico) {
		validarExistenciaPrevia(servicioElectrico);
		this.repositorioServicioElectrico.actualizar(servicioElectrico);
	}

	private void validarExistenciaPrevia(ServicioElectrico servicioElectrico) {
		boolean existe = repositorioServicioElectrico.existePorId (servicioElectrico.getId());
		if(!existe) {
			throw new ExcepcionDuplicidad(SERVICIO_NO_EXISTE);
		}
	}
	
	

}
