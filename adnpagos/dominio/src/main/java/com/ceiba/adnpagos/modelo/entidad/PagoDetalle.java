package com.ceiba.adnpagos.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagoDetalle {
	private Long id;
	private String descripcion;
	private Double valor;
	private Long idServicio;
	private Long idPago;
	
}
