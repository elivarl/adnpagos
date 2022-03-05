package com.ceiba.adnpagos.comando;


import java.time.LocalDateTime;
import java.util.List;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private List<ComandoPagoDetalle> pagosDetalle;

}
