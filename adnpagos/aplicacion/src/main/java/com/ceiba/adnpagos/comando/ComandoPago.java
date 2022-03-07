package com.ceiba.adnpagos.comando;


import java.time.LocalDateTime;
import java.util.List;


import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double subTotal;
	private Double total;
	private List<ComandoServicioElectrico> comandoServicioElectricos;

}
