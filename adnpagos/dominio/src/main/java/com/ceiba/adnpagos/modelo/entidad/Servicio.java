package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Servicio {
	//private static final String SERVICIO_YA_EXISTE="El número de servicio ya existe";
	
	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private LocalDateTime fechaCreacion;
	
	

}
