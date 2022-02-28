package com.ceiba.adnpagos.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.servicio.ServicioEliminarServicioElectrico;

@Component
public class ManejadorEliminarServicioElectrico implements ManejadorComando<Long> {
	
	private final ServicioEliminarServicioElectrico servicioEliminarServicioElectrico;
	
	public ManejadorEliminarServicioElectrico(ServicioEliminarServicioElectrico servicioEliminarServicioElectrico) {
		this.servicioEliminarServicioElectrico=servicioEliminarServicioElectrico;	
	}
	
	
	@Override
	public void ejecutar(Long idServicioElectrico) {
		this.servicioEliminarServicioElectrico.ejecutar(idServicioElectrico);
	}

}
