package com.ceiba.pago.testdatabuilder;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

import java.time.LocalDateTime;

public class ServicioElectricoTestDataBuilder {
    private Long id;
    private String numeroServicio;
    private String identificacionCliente;
    private String nombreCliente;
    private String mesPago;
    private LocalDateTime fechaMaximaPago;
    private Double valor;
    private LocalDateTime fechaCreacion;

    public ServicioElectricoTestDataBuilder(){
        id=1L;
        numeroServicio="12345";
        identificacionCliente="1717213183";
        nombreCliente="Elivar Largo";
        mesPago="Enero";
        fechaMaximaPago= LocalDateTime.parse("2022-03-28T15:00:00.104");
        valor=10.0;
        fechaCreacion=LocalDateTime.now();
    }

    public ServicioElectricoTestDataBuilder conNumeroServicio(String  numeroServicio){
        this.numeroServicio=numeroServicio;
        return this;
    }

    public ServicioElectricoTestDataBuilder conIdentificacionCliente(String  identificacionCliente){
        this.identificacionCliente=identificacionCliente;
        return this;
    }

    public ServicioElectricoTestDataBuilder conNombreCliente(String  nombreCliente){
        this.nombreCliente=nombreCliente;
        return this;
    }
    public ServicioElectricoTestDataBuilder conMesPago(String  mesPago){
        this.mesPago=mesPago;
        return this;
    }

    public ServicioElectricoTestDataBuilder conFechaMaximaPago(LocalDateTime  fechaMaximaPago){
        this.fechaMaximaPago=fechaMaximaPago;
        return this;
    }

    public ServicioElectricoTestDataBuilder conValor(Double  valor){
        this.valor=valor;
        return this;
    }

    public ServicioElectricoTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion){
        this.fechaCreacion=fechaCreacion;
        return this;
    }

    public ServicioElectricoTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public ServicioElectrico build(){
        return new ServicioElectrico(id,numeroServicio, identificacionCliente, nombreCliente, mesPago, fechaMaximaPago, valor,fechaCreacion);
    }

}
