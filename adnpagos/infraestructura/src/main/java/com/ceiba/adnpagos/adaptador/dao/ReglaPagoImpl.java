package com.ceiba.adnpagos.adaptador.dao;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.puerto.dao.ReglaPago;

@Component
public class ReglaPagoImpl implements ReglaPago {

	@Override
	public long calcularDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		Duration d= Duration.between(fechaInicial, fechaFinal);		
		System.out.println("calcularDias "+d.toDays());
		return d.toDays();
	}


	@Override
	public double calcularValorDescuento(Double subtotal, long porcentajeDescuento) {		
		return (subtotal*porcentajeDescuento)/100;
	}

	@Override
	public long obtenerPorcentajeDescuento(long dias) {
		if (dias > 3) {
			return 8;
		}else if(dias<=3&&dias>=0) {
			return 0;
		}
		return 10;
	}

	@Override
	public double calcularTotalPago(Double subtotal, long porcentajeDescuento, long dias) {
		if(dias>0) {
			return subtotal-((subtotal*porcentajeDescuento)/100);
		}
		return subtotal+((subtotal*porcentajeDescuento)/100);
	}	
	
}
