package com.ceiba.adnpagos.comando;


import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {
	private Long id;
	private String identificacionCliente;
	private List<ComandoPagoDetalle> pagosDetalle;

}
