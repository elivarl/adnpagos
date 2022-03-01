package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

public interface RepositorioPago {
	Long crear (Pago pago);
	public void actualizar (Pago pago);
	public void elimnar(Long id);
	public void existePorId(Long id);
	
	//métodos detalle
	public void crearPagoDetalle(PagoDetalle pagoDetalle);

}
