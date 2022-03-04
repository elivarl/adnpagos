package com.ceiba.adnpagos.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;
import lombok.Getter;

@Getter
public class PagoDetalle {
	private  final String OBLIGATORIO_DESCRIPCION="Se debe ingresar la descripcion para el mes de pago";
	private  final String OBLIGATORIO_VALOR="Se debe ingresar el valor del pago para el mes de servicio";
	private  final String OBLIGATORIO_ID_SERVICIO="Se debe ingresar el ID del servicio par el detalle del pago";
	private  final String OBLIGATORIO_ID_PAGO="Se debe ingresar el ID del pago para el detalle";

	private final String VALIDAR_POSITIVO="Se debe ingresar un valor positivo";

	private Long id;
	private String descripcion;
	private Double valor;
	private Long idServicio;
	private Long idPago;

	public PagoDetalle(Long id, String descripcion, Double valor, Long idServicio, Long idPago) {
		validarObligatorio(descripcion, OBLIGATORIO_DESCRIPCION);
		validarObligatorio(valor, OBLIGATORIO_VALOR);
		validarObligatorio(idServicio, OBLIGATORIO_ID_SERVICIO);
		validarObligatorio(idPago, OBLIGATORIO_ID_PAGO);

		validarPositivo(valor, VALIDAR_POSITIVO);
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
		this.idServicio = idServicio;
		this.idPago = idPago;
	}
}

