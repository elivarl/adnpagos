package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.Pago;

public class ServicioAplicarReglaPago {

    //reglas antes de crear el pago en la base de datos
    public Pago aplicarReglas(Pago pago){
        pago.setReglapFechaPagoLaboral();
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
        pago.setPagoDetalles();
        pago.setEstadoServicio();
        return pago;
    }
}
