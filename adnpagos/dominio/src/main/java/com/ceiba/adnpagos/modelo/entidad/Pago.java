package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

@Getter
public class Pago {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double subTotal;
	private String porcentajeDescuentoRecargo;
	private Double valorDescuentoRecargo;
	private Double total;
	private List<PagoDetalle> pagosDetalle;

	private  final String OBLIGATORIO_FECHA_PAGO="Se debe ingresar el número de servicio";
	private final String OBLIGATORIO_IDENTIFICACION_CLIENTE="Se debe ingresar la identificación del cliente";
	private  final String OBLIGATORIO_SUBTOTAL="Se debe ingresar el subtotal";
	private final String OBLIGATORIO_PORCENTAJE_DESCUENTO_RECARGO="Se debe ingresar el porcentanje descuento/recargo";
	private final String OBLIGATORIO_VALOR_DESCUENTO_RECARGO="Se debe ingresar el valor descuento/recargo";
	private final String OBLIGATORIO_TOTAL="Se debe ingresar el valor total";
	private final String OBLIGATORIO_PAGOS_DETALLE="Se debe ingresar el detalle de pagos";

	private final String VALIDAR_POSITIVO="Se debe ingresar un valor positivo";


	public Pago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double subTotal, String porcentajeDescuentoRecargo, Double valorDescuentoRecargo, Double total, List<PagoDetalle> pagosDetalle) {

		validarObligatorio(fechaPago,OBLIGATORIO_FECHA_PAGO);
		validarObligatorio(identificacionCliente,OBLIGATORIO_IDENTIFICACION_CLIENTE);
		validarObligatorio(subTotal,OBLIGATORIO_SUBTOTAL);
		validarObligatorio(porcentajeDescuentoRecargo,OBLIGATORIO_PORCENTAJE_DESCUENTO_RECARGO);
		validarObligatorio(valorDescuentoRecargo,OBLIGATORIO_VALOR_DESCUENTO_RECARGO);
		validarObligatorio(total,OBLIGATORIO_TOTAL);
		validarObligatorio(pagosDetalle,OBLIGATORIO_PAGOS_DETALLE);

		validarPositivo(subTotal, VALIDAR_POSITIVO);
		validarPositivo(total, VALIDAR_POSITIVO);

		this.id = id;
		this.fechaPago = fechaPago;
		this.identificacionCliente = identificacionCliente;
		this.subTotal = subTotal;
		this.porcentajeDescuentoRecargo = porcentajeDescuentoRecargo;
		this.valorDescuentoRecargo = valorDescuentoRecargo;
		this.total = total;
		this.pagosDetalle = pagosDetalle;
	}

}
