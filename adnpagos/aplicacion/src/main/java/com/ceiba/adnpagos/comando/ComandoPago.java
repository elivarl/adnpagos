package com.ceiba.adnpagos.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double valorTotal;

}
