package com.ceiba.adnpagos.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

@Getter
public class ServicioElectrico {
	private  final String OBLIGATORIO_NUMERO_SERVICIO="Se debe ingresar el número de servicio";
	private final String OBLIGATORIO_IDENTIFICACION_CLIENTE="Se debe ingresar la identificación del cliente";
	private final String OBLIGATORIO_NOMBRE_CLIENTE="Se debe ingresar el nombre del cliente";
	private final String OBLIGATORIO_MES="Se debe ingresar el mes correspondiente al servicio";
	private final String OBLIGATORIO_FECHA_MAXIMO_PAGO="Se debe ingresar la fecha máxima de pago";
	private final String OBLIGATORIO_VALOR="Se debe ingresar el valor del servicio";
	private final String OBLIGATORIO_ESTADO="Se debe ingresar el estado del servicio";
	private final String OBLIGATORIO_FECHA_CREACION="Se debe ingresar la fecha de creación";

	private final String VALIDAR_POSITIVO="Se debe ingresar un valor positivo";

	private Long id;
	private String numeroServicio;
	private String identificacionCliente;
	private String nombreCliente;
	private String mes;
	private LocalDateTime fechaMaximaPago;
	private Double valor;
	private boolean estado;
	private LocalDateTime fechaCreacion;

	public ServicioElectrico(Long id, String numeroServicio, String identificacionCliente, String nombreCliente, String mes, LocalDateTime fechaMaximaPago, Double valor, boolean estado, LocalDateTime fechaCreacion) {

		validarObligatorio(fechaCreacion,OBLIGATORIO_NUMERO_SERVICIO);
		validarObligatorio(identificacionCliente, OBLIGATORIO_IDENTIFICACION_CLIENTE);
		validarObligatorio(nombreCliente, OBLIGATORIO_NOMBRE_CLIENTE);
		validarObligatorio(mes, OBLIGATORIO_MES);
		validarObligatorio(fechaMaximaPago, OBLIGATORIO_FECHA_MAXIMO_PAGO);
		validarObligatorio(valor, OBLIGATORIO_VALOR);
		validarObligatorio(estado, OBLIGATORIO_ESTADO);
		validarObligatorio(fechaCreacion, OBLIGATORIO_FECHA_CREACION);

		validarPositivo(valor, VALIDAR_POSITIVO);


		this.id = id;
		this.numeroServicio = numeroServicio;
		this.identificacionCliente = identificacionCliente;
		this.nombreCliente = nombreCliente;
		this.mes = mes;
		this.fechaMaximaPago = fechaMaximaPago;
		this.valor = valor;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
