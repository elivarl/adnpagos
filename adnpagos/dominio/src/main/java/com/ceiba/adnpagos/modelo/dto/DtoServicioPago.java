package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DtoServicioPago {
	private Long id;
	private String mesPago;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private LocalDateTime fechaCreacion;
	private DtoServicio servicio;

}
