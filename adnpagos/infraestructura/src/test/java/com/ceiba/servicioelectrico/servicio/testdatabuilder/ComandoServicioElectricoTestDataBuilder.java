package com.ceiba.servicioelectrico.servicio.testdatabuilder;

import com.ceiba.adnpagos.comando.ComandoServicioElectrico;

import java.time.LocalDateTime;

public class ComandoServicioElectricoTestDataBuilder {
    private Long id;
    private String numeroServicio;
    private String identificacionCliente;
    private String nombreCliente;
    private String mesPago;
    private LocalDateTime fechaMaximaPago;
    private Double valor;
    private LocalDateTime fechaCreacion;

    public ComandoServicioElectricoTestDataBuilder(){
        id=1L;
        numeroServicio="12345";
        identificacionCliente="1717213183";
        nombreCliente="Elivar Largo";
        mesPago="Enero";
        fechaMaximaPago= LocalDateTime.parse("2022-03-28T15:00:00.104");
        valor=120.0;
        fechaCreacion=LocalDateTime.now();
    }

    public ComandoServicioElectricoTestDataBuilder conNumeroServicio(String  numeroServicio){
        this.numeroServicio=numeroServicio;
        return this;
    }

    public ComandoServicioElectricoTestDataBuilder conIdentificacionCliente(String  identificacionCliente){
        this.identificacionCliente=identificacionCliente;
        return this;
    }

    public ComandoServicioElectricoTestDataBuilder conNombreCliente(String  nombreCliente){
        this.nombreCliente=nombreCliente;
        return this;
    }
    public ComandoServicioElectricoTestDataBuilder conMesPago(String  mesPago){
        this.mesPago=mesPago;
        return this;
    }

    public ComandoServicioElectricoTestDataBuilder conFechaMaximaPago(LocalDateTime  fechaMaximaPago){
        this.fechaMaximaPago=fechaMaximaPago;
        return this;
    }

    public ComandoServicioElectricoTestDataBuilder conValor(Double  valor){
        this.valor=valor;
        return this;
    }


    public ComandoServicioElectricoTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion){
        this.fechaCreacion=fechaCreacion;
        return this;
    }

    public ComandoServicioElectricoTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public ComandoServicioElectrico build(){
        return new ComandoServicioElectrico(id,numeroServicio, identificacionCliente, nombreCliente, mesPago, fechaMaximaPago, valor,fechaCreacion);
    }
}
