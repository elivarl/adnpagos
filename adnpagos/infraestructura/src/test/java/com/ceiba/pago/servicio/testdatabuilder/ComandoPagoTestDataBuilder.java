package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComandoPagoTestDataBuilder {
    private Long id;
    private LocalDateTime fechaPago;
    private String identificacionCliente;
    private List<ComandoPagoDetalleTestDataBuilder> pagosDetalleTestDataBuilder;

    public ComandoPagoTestDataBuilder(){
        id=0L;
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

    public ComandoPagoTestDataBuilder conPagoDetalles(List<ComandoPagoDetalleTestDataBuilder> pagosDetalleTestDataBuilder) {
        this.pagosDetalleTestDataBuilder=pagosDetalleTestDataBuilder;
        return this;
    }

    public ComandoPago build(){
        return new ComandoPago(id, fechaPago,identificacionCliente, obtenerPagoDetalle(pagosDetalleTestDataBuilder));
    }
    
    private List<ComandoPagoDetalle> obtenerPagoDetalle (List<ComandoPagoDetalleTestDataBuilder> comandoPagoDetalleTestDataBuilders){
        List<ComandoPagoDetalle> comandoPagoDetalles= new ArrayList<>();
        for (ComandoPagoDetalleTestDataBuilder comandoPagoDetalleTestDataBuilder:comandoPagoDetalleTestDataBuilders
                 ) {
            comandoPagoDetalles.add(comandoPagoDetalleTestDataBuilder.build());
        }

        return comandoPagoDetalles;
    }

}
