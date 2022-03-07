package com.ceiba.adnpagos.comando.manejador;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.fabrica.FabricaPago;
import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.usuario.servicio.ServicioAplicarReglaPago;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAplicarReglasPago {

    private final ServicioAplicarReglaPago servicioAplicarReglaPago;
    private final FabricaPago fabricaPago;

    public ManejadorAplicarReglasPago(ServicioAplicarReglaPago servicioAplicarReglaPago, FabricaPago fabricaPago) {
        this.servicioAplicarReglaPago=servicioAplicarReglaPago;
        this.fabricaPago=fabricaPago;
    }

    public DtoPago aplicarReglasPago(ComandoPago comandoPago){
        Pago pago=servicioAplicarReglaPago.aplicarReglas(this.fabricaPago.crear(comandoPago));
        return new DtoPago(pago.getId(), pago.getFechaPago(),pago.getIdentificacionCliente(),pago.getTotal(),pago.getPagoServicios());
    }
}
