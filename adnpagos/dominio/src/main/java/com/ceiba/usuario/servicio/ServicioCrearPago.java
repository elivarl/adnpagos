package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;

public class ServicioCrearPago {
	
	private final RepositorioPago repositorioPago;
	
	
	public ServicioCrearPago(RepositorioPago repositorioPago) {
		this.repositorioPago=repositorioPago;
	}
	
	public Long ejecutar (Pago pago) {
		Long idPago=this.repositorioPago.crear(pago);
		System.out.println("Id pago: "+idPago);
		
		for (PagoDetalle pagoDetalle: pago.getPagosDetalle()) {
			pagoDetalle.setIdPago(idPago);
			System.out.println("Pago detalle idPago: "+pagoDetalle.getIdPago());
			System.out.println("Pago detalle idServicio: "+pagoDetalle.getIdServicio());
			crearPagoDetalle(pagoDetalle);
		}
		return idPago;
	}
	
	private void crearPagoDetalle(PagoDetalle pagoDetalle) {
		repositorioPago.crearPagoDetalle(pagoDetalle);
	}

}
