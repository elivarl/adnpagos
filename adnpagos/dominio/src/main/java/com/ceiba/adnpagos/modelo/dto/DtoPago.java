package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DtoPago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double valorTotal;
}
