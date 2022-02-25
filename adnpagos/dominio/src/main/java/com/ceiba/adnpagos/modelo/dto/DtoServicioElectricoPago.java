package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DtoServicioElectricoPago {
	private Long id;
	private String mesPago;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private LocalDateTime fechaCreacion;
	private DtoServicioElectrico servicio;

}
