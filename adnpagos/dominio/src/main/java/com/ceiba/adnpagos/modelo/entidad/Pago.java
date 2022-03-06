package com.ceiba.adnpagos.modelo.entidad;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Pago {
	private static final String OBLIGATORIO_FECHA_PAGO="Se debe ingresar la fecha de pago";
	private  static final String OBLIGATORIO_IDENTIFICACION_CLIENTE="Se debe ingresar la identificación del cliente";
	private  static final String OBLIGATORIO_SUBTOTAL="Se debe ingresar el subtotal";
	//private  static final String OBLIGATORIO_PORCENTAJE_DESCUENTO_RECARGO="Se debe ingresar el porcentanje descuento/recargo";
	//private  static final String OBLIGATORIO_VALOR_DESCUENTO_RECARGO="Se debe ingresar el valor descuento/recargo";
	private  static final String OBLIGATORIO_TOTAL="Se debe ingresar el valor total";
	private  static final String OBLIGATORIO_PAGOS_DETALLE="Se debe ingresar el detalle de los servicios a pagar";


	private static final long SUMAR_UN_DIA=1;
	private static final long SUMAR_DOS_DIAS=2;
	private static final int NUMERO_MAXIMO_DETALLES_APLICA_DESCUENTO_RECARGO=1;
	private static final String POR_DEFECTO_PORCENTAJE_DESCUENTO_RECARGO="0";
	private static final Double POR_DEFECTO_VALOR_DESCUENTO_RECARGO=0.0;
	private static final long DIAS_LABORASLES_INICIALIZACION=0;
	private static final long VALOR_CIEN=100;
	private static final long MAYOR_A_3_DIAS=3;
	private static final long PORCENTAJE_DESCUENTO_MAYOR_A_3_DIAS=8;
	private static final long MAYOR_A_1_DIA=1;
	private static final long MENOR_A_3_DIA=3;
	private static final long PORCENTAJE_DESCUENTO_1_A_3_DIAS=0;
	private static final long PORCENTAJE_RECARGO_PAGO_MAYOR_FECHA_PAGO=10;
	private static final long DIAS_ENTRE_FECHAS=0;
	private static final int INDICE_SERVICIO=0;


	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double subTotal;
	private String porcentajeDescuentoRecargo;
	private Double valorDescuentoRecargo;
	private Double total;
	private List<ServicioElectrico> pagoServicios;


	public Pago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double subTotal, Double total, List<ServicioElectrico> pagoServicios) {

		validarObligatorio(fechaPago,OBLIGATORIO_FECHA_PAGO);
		validarObligatorio(identificacionCliente,OBLIGATORIO_IDENTIFICACION_CLIENTE);
		validarObligatorio(subTotal,OBLIGATORIO_SUBTOTAL);
		//validarObligatorio(porcentajeDescuentoRecargo,OBLIGATORIO_PORCENTAJE_DESCUENTO_RECARGO);
		//validarObligatorio(valorDescuentoRecargo,OBLIGATORIO_VALOR_DESCUENTO_RECARGO);
		validarObligatorio(total,OBLIGATORIO_TOTAL);
		validarObligatorio(pagoServicios,OBLIGATORIO_PAGOS_DETALLE);

		this.id = id;
		this.fechaPago = fechaPago;
		this.identificacionCliente = identificacionCliente;
		this.subTotal = subTotal;
		this.porcentajeDescuentoRecargo=POR_DEFECTO_PORCENTAJE_DESCUENTO_RECARGO;
		this.valorDescuentoRecargo=POR_DEFECTO_VALOR_DESCUENTO_RECARGO;
		this.total = total;
		this.pagoServicios = pagoServicios;
	}

	public void setReglapFechaPagoLaboral(){
		setFechaLaboralPagoCuandoEsFinDeSemana();
	}
	public void setAplicarReglaPorcentajeDescuentoRecargo(){
		realizarReglasPago();
	}


	private void setFechaLaboralPagoCuandoEsFinDeSemana() {
		if (this.fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())
				|| fechaPago.getDayOfWeek().name().equals(NoLaboral.SUNDAY.toString())) {

			if (fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())) {
				this.fechaPago= fechaPago.plusDays(SUMAR_DOS_DIAS);
			} else {
				this.fechaPago= fechaPago.plusDays(SUMAR_UN_DIA);
			}
		}
	}

	private void realizarReglasPago() {
		if (this.getPagoServicios().size() ==NUMERO_MAXIMO_DETALLES_APLICA_DESCUENTO_RECARGO) {
			long dias = calcularDias(this.fechaPago,getPagoServicios().get(INDICE_SERVICIO).getFechaMaximaPago());
			long porcentajeDescuentoRecargo = obtenerPorcentajeDescuento(dias);
			double valorDescuentoRecargo = calcularValorDescuentoRecargo(this.getSubTotal(), porcentajeDescuentoRecargo);
			double totalPago = calcularTotalPago(this.getSubTotal(), porcentajeDescuentoRecargo, dias);
			this.valorDescuentoRecargo=valorDescuentoRecargo;
			this.porcentajeDescuentoRecargo=String.valueOf(porcentajeDescuentoRecargo);
			this.total=totalPago;
		}
	}

	private long calcularDias(LocalDateTime fechaPago, LocalDateTime fechaMaximaPago) {
		Duration totalDias = Duration.between(fechaPago, fechaMaximaPago);
		LocalDateTime  fechaInicial= fechaPago;
		long diasLaborales = DIAS_LABORASLES_INICIALIZACION;
		for (int i = 1; i <= totalDias.toDays(); i++) {
			String dia = fechaInicial.plusDays(i).getDayOfWeek().name();
			if (!dia.equals("SATURDAY")) {
				if (!dia.equals("SUNDAY")) {
					diasLaborales++;
				}
			}

		}
		return diasLaborales;
	}

	private double calcularValorDescuentoRecargo(Double subtotal, long porcentajeDescuento) {
		return (subtotal * porcentajeDescuento) / VALOR_CIEN;
	}

	private long obtenerPorcentajeDescuento(long dias) {
		if (dias > MAYOR_A_3_DIAS) {
			return PORCENTAJE_DESCUENTO_MAYOR_A_3_DIAS;
		} else if (dias <= MENOR_A_3_DIA && dias >= MAYOR_A_1_DIA) {
			return PORCENTAJE_DESCUENTO_1_A_3_DIAS;
		}
		return PORCENTAJE_RECARGO_PAGO_MAYOR_FECHA_PAGO;
	}

	private double calcularTotalPago(Double subtotal, long porcentajeDescuento, long dias) {
		if (dias > DIAS_ENTRE_FECHAS) {
			return subtotal - ((subtotal * porcentajeDescuento) / VALOR_CIEN);
		}
		return subtotal + ((subtotal * porcentajeDescuento) / VALOR_CIEN);
	}

}

