package com.ceiba.adnpagos.puerto.repositorio;

import com.ceiba.adnpagos.modelo.entidad.Pago;

public interface RepositorioPago {
	Long crear (Pago pago);
	public void actualizar (Pago pago);
	public void elimnar(Long id);
	public void existePorId(Long id);

}
