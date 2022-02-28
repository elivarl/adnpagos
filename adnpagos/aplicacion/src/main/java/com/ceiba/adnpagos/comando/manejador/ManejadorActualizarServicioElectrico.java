package com.ceiba.adnpagos.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.comando.fabrica.FabricaServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarServicioElectrico implements ManejadorComando<ComandoServicioElectrico>{
	
	private final FabricaServicioElectrico fabricaServicioElectrico;
	private final RepositorioServicioElectrico repositorioServicioElectrico;
	
	public ManejadorActualizarServicioElectrico(FabricaServicioElectrico fabricaServicioElectrico, RepositorioServicioElectrico repositorioServicioElectrico) {
		this.fabricaServicioElectrico=fabricaServicioElectrico;
		this.repositorioServicioElectrico=repositorioServicioElectrico;	
	}

	@Override
	public void ejecutar(ComandoServicioElectrico comandoServicioElectrico) {
		ServicioElectrico servicioElectrico=fabricaServicioElectrico.crear(comandoServicioElectrico);
		this.repositorioServicioElectrico.actualizar(servicioElectrico);
	}	
	

}
