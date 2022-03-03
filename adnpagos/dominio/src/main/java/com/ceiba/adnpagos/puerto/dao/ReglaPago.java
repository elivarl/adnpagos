package com.ceiba.adnpagos.puerto.dao;

import java.time.LocalDateTime;

public interface ReglaPago {
	
	public long calcularDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
	
	public double calcularValorDescuento(Double subtotal, long porcentajeDescuento);
	
	public long obtenerPorcentajeDescuento(long dias);
	
	public double calcularTotalPago(Double subtotal, long porcentajeDescuento, long dias);
	 
}
