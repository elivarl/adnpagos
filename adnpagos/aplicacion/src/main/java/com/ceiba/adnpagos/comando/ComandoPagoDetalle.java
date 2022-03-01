package com.ceiba.adnpagos.comando;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComandoPagoDetalle {
	private Long id;
	private String descripcion;
	private Long idServicio;
	private Long idPago;

}
