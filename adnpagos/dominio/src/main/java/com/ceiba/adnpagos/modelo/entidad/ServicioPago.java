package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ServicioPago {
	private Long id;
	private String mesPago;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private LocalDateTime fechaCreacion;
	private Servicio servicio;

}
