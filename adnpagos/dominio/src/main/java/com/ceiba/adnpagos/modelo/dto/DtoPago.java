package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DtoPago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double valorTotal;
	private List<DtoServicioElectrico> dtoServiciosPago;

	public DtoPago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double valorTotal) {
		this.id = id;
		this.fechaPago = fechaPago;
		this.identificacionCliente = identificacionCliente;
		this.valorTotal = valorTotal;
	}
}
