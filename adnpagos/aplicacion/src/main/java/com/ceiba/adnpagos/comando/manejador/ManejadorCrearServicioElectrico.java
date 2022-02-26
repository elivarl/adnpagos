package com.ceiba.adnpagos.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.comando.fabrica.FabricaServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.servicio.ServicioCrearServicioElectrico;

@Component
public class ManejadorCrearServicioElectrico
		implements ManejadorComandoRespuesta<ComandoServicioElectrico, ComandoRespuesta<Long>> {
	private final FabricaServicioElectrico fabricaServicioElectrico;
	private final ServicioCrearServicioElectrico crearServicioElectrico;

	public ManejadorCrearServicioElectrico(FabricaServicioElectrico fabricaServicioElectrico,
			ServicioCrearServicioElectrico crearServicioElectrico) {
		this.fabricaServicioElectrico = fabricaServicioElectrico;
		this.crearServicioElectrico = crearServicioElectrico;

	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoServicioElectrico comando) {
		ServicioElectrico servicioElectrico = fabricaServicioElectrico.crear(comando);
		return new ComandoRespuesta<Long>(crearServicioElectrico.ejecutar(servicioElectrico));
	}

}
