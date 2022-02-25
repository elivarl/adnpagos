package com.ceiba.adnpagos.modelo.entidad;

import lombok.Getter;

@Getter
public class PagoDetalle {
	private Long id;
	private String descripcion;
	private Pago pago;
	
}
