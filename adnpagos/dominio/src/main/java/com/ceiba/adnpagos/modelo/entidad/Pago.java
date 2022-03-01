package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double valorTotal;
	private List<PagoDetalle> pagosDetalle;
	
	public void aniadirPago(PagoDetalle detalle) {
		pagosDetalle.add(detalle);
	}
}
