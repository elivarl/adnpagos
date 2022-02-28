package com.ceiba.adnpagos.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandoServicioElectrico {	
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
