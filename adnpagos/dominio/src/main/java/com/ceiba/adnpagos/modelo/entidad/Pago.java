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
	private Double subTotal;
	private String porcentajeDescuento;
	private Double valorDescuento;
	private Double total;
	private List<PagoDetalle> pagosDetalle;
	
	public void aniadirPago(PagoDetalle detalle) {
		pagosDetalle.add(detalle);
	}
}
