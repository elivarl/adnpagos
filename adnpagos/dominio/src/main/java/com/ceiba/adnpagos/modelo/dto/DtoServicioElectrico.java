package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DtoServicioElectrico {
	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private String mes;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private boolean estado;

	public DtoServicioElectrico(Long id, String numeroServicio, String identificacionCliente, String nombreCliente, String mes, LocalDateTime fechaMaximaPago, Double valor, boolean estado) {
		this.id = id;
		this.numeroServicio = numeroServicio;
		this.identificacionCliente = identificacionCliente;
		this.nombreCliente = nombreCliente;
		this.mes = mes;
		this.fechaMaximaPago = fechaMaximaPago;
		this.valor = valor;
		this.estado = estado;
	}
}
