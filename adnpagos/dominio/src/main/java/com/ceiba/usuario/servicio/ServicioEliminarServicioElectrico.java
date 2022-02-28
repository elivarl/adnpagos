package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;

public class ServicioEliminarServicioElectrico {
	
	private final RepositorioServicioElectrico repositorioServicioElectrico;
	
	public ServicioEliminarServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioServicioElectrico=repositorioServicioElectrico;
	}
	
	public void ejecutar (Long id) {
		this.repositorioServicioElectrico.eliminar(id);
	}
	

}
