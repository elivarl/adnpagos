package com.ceiba.adnpagos.modelo.entidad;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Pago {
    private static final String OBLIGATORIO_FECHA_PAGO = "Se debe ingresar la fecha de pago";
    private static final String OBLIGATORIO_IDENTIFICACION_CLIENTE = "Se debe ingresar la identificación del cliente";
    private static final String OBLIGATORIO_SUBTOTAL = "Se debe ingresar el subtotal";
    private static final String DIA_NO_LABORAL_SABADO = "SATURDAY";
    private  static final String DIA_NO_LABORAL_DOMINGO="SUNDAY";
    private static final String OBLIGATORIO_TOTAL = "Se debe ingresar el valor total";
    private static final String OBLIGATORIO_PAGOS_DETALLE = "Se debe ingresar el detalle de los servicios a pagar";


    private static final long SUMAR_UN_DIA = 1;
    private static final long SUMAR_DOS_DIAS = 2;
    private static final int NUMERO_MAXIMO_DETALLES_APLICA_DESCUENTO_RECARGO = 1;
    private static final String POR_DEFECTO_PORCENTAJE_DESCUENTO_RECARGO = "0";
    private static final Double POR_DEFECTO_VALOR_DESCUENTO_RECARGO = 0.0;
    private static final long DIAS_LABORASLES_INICIALIZACION = 0;
    private static final long VALOR_CIEN = 100;
    private static final long MAYOR_A_3_DIAS = 3;
    private static final long PORCENTAJE_DESCUENTO_MAYOR_A_3_DIAS = 8;
    private static final long MAYOR_A_1_DIA = 1;
    private static final long PORCENTAJE_DESCUENTO_1_A_3_DIAS = 0;
    private static final long PORCENTAJE_RECARGO_PAGO_MAYOR_FECHA_PAGO = 10;
    private static final long DIAS_ENTRE_FECHAS = 0;
    private static final int INDICE_SERVICIO = 0;


    private Long id;
    private LocalDateTime fechaPago;
    private String identificacionCliente;
    private Double subTotal;
    private String porcentajeDescuentoRecargo;
    private Double valorDescuentoRecargo;
    private Double total;
    private List<ServicioElectrico> pagoServicios;
    private List<PagoDetalle> pagoDetalles;


    public Pago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double subTotal, Double total, List<ServicioElectrico> pagoServicios) {

        validarObligatorio(fechaPago, OBLIGATORIO_FECHA_PAGO);
        validarObligatorio(identificacionCliente, OBLIGATORIO_IDENTIFICACION_CLIENTE);
        validarObligatorio(subTotal, OBLIGATORIO_SUBTOTAL);
        validarObligatorio(total, OBLIGATORIO_TOTAL);
        validarObligatorio(pagoServicios, OBLIGATORIO_PAGOS_DETALLE);

        this.id = id;
        this.fechaPago = fechaPago;
        this.identificacionCliente = identificacionCliente;

        this.porcentajeDescuentoRecargo = POR_DEFECTO_PORCENTAJE_DESCUENTO_RECARGO;
        this.valorDescuentoRecargo = POR_DEFECTO_VALOR_DESCUENTO_RECARGO;
        this.pagoServicios = pagoServicios;
        this.subTotal = sumarTotal();
        this.total = total;
        this.pagoDetalles = new ArrayList<>();
    }

    public void setReglapFechaPagoLaboral() {
        setFechaLaboralPagoCuandoEsFinDeSemana();
    }

    public void setAplicarReglaPorcentajeDescuentoRecargo() {
        realizarReglasPago();
    }


    private void setFechaLaboralPagoCuandoEsFinDeSemana() {
        if (this.fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())
                || fechaPago.getDayOfWeek().name().equals(NoLaboral.SUNDAY.toString())) {

            if (fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())) {
                this.fechaPago = fechaPago.plusDays(SUMAR_DOS_DIAS);
            } else {
                this.fechaPago = fechaPago.plusDays(SUMAR_UN_DIA);
            }
        }
    }

    private void realizarReglasPago() {
        if (this.getPagoServicios().size() == NUMERO_MAXIMO_DETALLES_APLICA_DESCUENTO_RECARGO) {
            long dias = calcularDias(this.fechaPago, getPagoServicios().get(INDICE_SERVICIO).getFechaMaximaPago());
            this.porcentajeDescuentoRecargo=String.valueOf(obtenerPorcentajeDescuentoRecargo(dias));
            this.valorDescuentoRecargo   = calcularValorDescuentoRecargo(this.getSubTotal(), Long.parseLong(this.porcentajeDescuentoRecargo));

            this.total = calcularTotalPago(this.getSubTotal(), this.valorDescuentoRecargo , dias);
        }else{
            this.total=sumarTotal();
        }
    }

    private long calcularDias(LocalDateTime fechaPago, LocalDateTime fechaMaximaPago) {
        Duration totalDias = Duration.between(fechaPago, fechaMaximaPago);
        long diasLaborales = DIAS_LABORASLES_INICIALIZACION;
        for (int i = 1; i <= totalDias.toDays(); i++) {
            String dia = fechaPago.plusDays(i).getDayOfWeek().name();
            if (!dia.equals(DIA_NO_LABORAL_SABADO)) {
                if (!dia.equals(DIA_NO_LABORAL_DOMINGO)) {
                    diasLaborales++;
                }
            }

        }
        return diasLaborales;
    }

    private double calcularValorDescuentoRecargo(Double subtotal, long porcentajeDescuento) {
        return (subtotal * porcentajeDescuento) / VALOR_CIEN;
    }

    private long obtenerPorcentajeDescuentoRecargo(long dias) {
        if (dias > MAYOR_A_3_DIAS) {
            return PORCENTAJE_DESCUENTO_MAYOR_A_3_DIAS;
        } else if (dias >= MAYOR_A_1_DIA) {
            return PORCENTAJE_DESCUENTO_1_A_3_DIAS;
        }
        return PORCENTAJE_RECARGO_PAGO_MAYOR_FECHA_PAGO;
    }

    private double calcularTotalPago(Double subtotal, Double valorDescuentoRecargo, long dias) {
        if (dias > DIAS_ENTRE_FECHAS) {
            return subtotal - valorDescuentoRecargo;
        }
        return subtotal + valorDescuentoRecargo;
    }

    public void setPagoDetalles() {
        for (ServicioElectrico servicioElectrico : this.getPagoServicios()
        ) {
            this.pagoDetalles.add(new PagoDetalle(null, "Pago del servicio del mes de : " + servicioElectrico.getMes(), servicioElectrico.getValor(), servicioElectrico.getId()));
        }
    }


    public void setEstadoServicio() {
        for (ServicioElectrico servicioElectrico : this.getPagoServicios()
        ) {
            this.pagoServicios.remove(servicioElectrico);
            servicioElectrico.setEstado(true);
            this.pagoServicios.add(servicioElectrico);
        }
    }

    private double sumarTotal(){
        return this.pagoServicios.stream().mapToDouble(s->s.getValor()).sum();
    }

}

