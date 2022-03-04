package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;

import java.util.UUID;

public class ComandoPagoDetalleTestDataBuilder {
    private Long idServicio;

    public ComandoPagoDetalleTestDataBuilder(){
        idServicio=1L;
    }

    public ComandoPagoDetalleTestDataBuilder conIdServicio(Long idServicio) {
        this.idServicio = idServicio;
        return this;
    }
    public ComandoPagoDetalle build(){ return new ComandoPagoDetalle(idServicio); }
}
