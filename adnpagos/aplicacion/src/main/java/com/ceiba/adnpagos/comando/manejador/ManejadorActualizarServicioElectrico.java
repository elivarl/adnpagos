package com.ceiba.adnpagos.comando.manejador;

import com.ceiba.usuario.servicio.ServicioActualizarServicioElectrico;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.comando.fabrica.FabricaServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarServicioElectrico implements ManejadorComando<ComandoServicioElectrico>{
	
	private final FabricaServicioElectrico fabricaServicioElectrico;
	private final ServicioActualizarServicioElectrico servicioActualizarServicioElectrico;
	
	public ManejadorActualizarServicioElectrico(FabricaServicioElectrico fabricaServicioElectrico, ServicioActualizarServicioElectrico servicioActualizarServicioElectrico) {
		this.fabricaServicioElectrico=fabricaServicioElectrico;
		this.servicioActualizarServicioElectrico=servicioActualizarServicioElectrico;
	}

	@Override
	public void ejecutar(ComandoServicioElectrico comandoServicioElectrico) {
		ServicioElectrico servicioElectrico=fabricaServicioElectrico.crear(comandoServicioElectrico);
		this.servicioActualizarServicioElectrico.ejeuctar(servicioElectrico);
	}	
	

}
