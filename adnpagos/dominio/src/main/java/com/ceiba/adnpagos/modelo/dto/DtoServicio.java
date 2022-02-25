package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DtoServicio {
	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private LocalDateTime fechaCreacion;

}
