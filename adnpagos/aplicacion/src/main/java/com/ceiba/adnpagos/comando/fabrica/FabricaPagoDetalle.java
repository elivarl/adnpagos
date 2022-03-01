package com.ceiba.adnpagos.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

@Component
public class FabricaPagoDetalle {

	
	public PagoDetalle crear(ComandoPagoDetalle comandoPagoDetalle) {
		return new PagoDetalle(comandoPagoDetalle.getId(), comandoPagoDetalle.getDescripcion(), comandoPagoDetalle.getIdServicio(), comandoPagoDetalle.getIdPago());
	}

}
