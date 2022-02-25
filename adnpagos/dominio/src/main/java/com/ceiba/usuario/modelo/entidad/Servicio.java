package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Servicio {
	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private LocalDateTime fechaCreacion;

}
