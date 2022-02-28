package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServicioElectrico {
		
	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private String mesPago;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private boolean estado;
	private LocalDateTime fechaCreacion;
	
	

}
