package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ComandoPagoTestDataBuilder {
    private Long id;
    private LocalDateTime fechaPago;
    private String identificacionCliente;
    private List<ComandoPagoDetalle> comandoPagosDetalle;

    public ComandoPagoTestDataBuilder(){
        id=null;
        fechaPago=LocalDateTime.now();
        identificacionCliente= UUID.randomUUID().toString();

    }

    public ComandoPagoTestDataBuilder conFechaPago(LocalDateTime fechaPago) {
        this.fechaPago=fechaPago;
        return this;
    }

    public ComandoPagoTestDataBuilder conIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente=identificacionCliente;
        return this;
    }

    public ComandoPagoTestDataBuilder conPagoDetalles(List<ComandoPagoDetalle> comandoPagosDetalle) {
        this.comandoPagosDetalle=comandoPagosDetalle;
        return this;
    }

    public ComandoPago build(){
        return new ComandoPago(id, fechaPago,identificacionCliente, comandoPagosDetalle);
    }

}
