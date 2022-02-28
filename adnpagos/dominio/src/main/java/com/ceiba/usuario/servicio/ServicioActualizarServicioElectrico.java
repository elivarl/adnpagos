package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;

public class ServicioActualizarServicioElectrico {
	
	private final RepositorioServicioElectrico repositorioServicioElectrico;
	
	public ServicioActualizarServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico=repositorioServicioElectrico;
	}
	
	public void ejeuctar (ServicioElectrico servicioElectrico) {
		this.repositorioServicioElectrico.actualizar(servicioElectrico);
	}
	
	

}
