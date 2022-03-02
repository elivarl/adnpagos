package com.ceiba.adnpagos.comando.fabrica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

@Component
public class FabricaPago {
	
		
	public Pago crear (ComandoPago comandoPago) {
		return new Pago(comandoPago.getId(), null, comandoPago.getIdentificacionCliente(),0.0,"", 0.0, 0.0, crearListaPagoDetalle(comandoPago.getPagosDetalle()));
	}
	
	private List<PagoDetalle> crearListaPagoDetalle(List<ComandoPagoDetalle> listaComandoPagoDetalle){
		
		List<PagoDetalle> pagosDetalle = new ArrayList<PagoDetalle>();
		
		for (ComandoPagoDetalle comandoPagoDetalle : listaComandoPagoDetalle) {
			pagosDetalle.add(new PagoDetalle(null, null, null,comandoPagoDetalle.getIdServicio(), null));
		}
		return pagosDetalle;
	}

}
