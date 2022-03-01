package com.ceiba.adnpagos.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.fabrica.FabricaPago;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.servicio.ServicioCrearPago;

@Component
public class ManejadorCrearPago implements ManejadorComandoRespuesta<ComandoPago, ComandoRespuesta<Long>> {
	
	private final FabricaPago fabricaPago;
	private final ServicioCrearPago servicioCrearPago;
	
	public ManejadorCrearPago(FabricaPago fabricaPago, ServicioCrearPago servicioCrearPago) {
		this.fabricaPago=fabricaPago;
		this.servicioCrearPago=servicioCrearPago;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoPago comandoPago) {
			Pago pago = this.fabricaPago.crear(comandoPago);
			return new ComandoRespuesta<>(this.servicioCrearPago.ejecutar(pago));
	}
	
}
