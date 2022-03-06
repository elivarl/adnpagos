package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.servicioelectrico.servicio.testdatabuilder.ComandoServicioElectricoTestDataBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComandoPagoTestDataBuilder {
    private Long id;
    private LocalDateTime fechaPago;
    private String identificacionCliente;
    private Double subTotal;
    private Double total;
    private List<ComandoServicioElectricoTestDataBuilder> comandoServicioElectricoTestDataBuilders;

    public ComandoPagoTestDataBuilder(){
        id=0L;
        fechaPago=LocalDateTime.now();
        subTotal=0.0;
        total=0.0;
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
    public ComandoPagoTestDataBuilder conSubTotal(Double subTotal) {
        this.subTotal=subTotal;
        return this;
    }
    public ComandoPagoTestDataBuilder conTotal(Double total) {
        this.total=total;
        return this;
    }

    public ComandoPagoTestDataBuilder conComandoServicioElectricoTestDataBuilders(List<ComandoServicioElectricoTestDataBuilder> comandoServicioElectricoTestDataBuilders) {
        this.comandoServicioElectricoTestDataBuilders=comandoServicioElectricoTestDataBuilders;
        return this;
    }

    public ComandoPago build(){
        return new ComandoPago(id, fechaPago,identificacionCliente,subTotal,total, convertirAComandoServicioElectricos(comandoServicioElectricoTestDataBuilders));
    }

    private List<ComandoServicioElectrico> convertirAComandoServicioElectricos(List<ComandoServicioElectricoTestDataBuilder> comandoServicioElectricoTestDataBuilders){
        List<ComandoServicioElectrico> servicioElectricos = new ArrayList<>();
        if(comandoServicioElectricoTestDataBuilders==null){
            for (ComandoServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder:comandoServicioElectricoTestDataBuilders
            ) {
                servicioElectricos.add(servicioElectricoTestDataBuilder.build());
            }
            return  servicioElectricos;
        }
        return  null;
    }

}
