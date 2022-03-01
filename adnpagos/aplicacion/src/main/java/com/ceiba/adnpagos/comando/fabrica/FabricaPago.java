package com.ceiba.adnpagos.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.modelo.entidad.Pago;

@Component
public class FabricaPago {
	
	public Pago crear (ComandoPago comandoPago) {
		return new Pago(comandoPago.getId(), comandoPago.getFechaPago(), comandoPago.getIdentificacionCliente(), comandoPago.getValorTotal());
	}

}
