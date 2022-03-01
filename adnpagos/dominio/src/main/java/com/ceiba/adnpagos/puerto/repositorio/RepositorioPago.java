package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

public interface RepositorioPago {
	Long crear (Pago pago);
	public void actualizar (Pago pago);
	public void eliminar(Long id);
	public boolean existePorId(Long id);
	
	//métodos detalle
	public void crearPagoDetalle(PagoDetalle pagoDetalle);

}
