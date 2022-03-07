package com.ceiba.usuario.servicio;

import com.ceiba.adnpagos.modelo.entidad.Pago;

public class ServicioAplicarReglaPago {

    public Pago aplicarReglas(Pago pago){
        pago.setReglapFechaPagoLaboral();
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
        return pago;
    }
}
