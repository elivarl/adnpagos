package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

public interface RepositorioPago {
	public Long crear (Pago pago);
	public boolean existePorId(Long id);
	
	//métodos detalle
	public Long crearPagoDetalle(PagoDetalle pagoDetalle);

	//metodo actualizar servicio cuando se hace el pago
	void actualizarServivioElectrico(ServicioElectrico servicio);

}
