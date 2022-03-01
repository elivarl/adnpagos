package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;

public class ServicioCrearPago {
	
	private final RepositorioPago repositorioPagos;
	
	
	public ServicioCrearPago(RepositorioPago repositorioPagos) {
		this.repositorioPagos=repositorioPagos;
	}
	
	public Long ejecutar (Pago pago) {
		return this.repositorioPagos.crear(pago);
	}

}
