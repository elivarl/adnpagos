package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Pago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double valorTotal;
}
