package com.ceiba.usuario.consulta;

import com.ceiba.adnpagos.modelo.dto.DtoPagoDetalle;
import com.ceiba.adnpagos.puerto.dao.DaoPagoDetalle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPagoDetalle {

    private final DaoPagoDetalle daoPagoDetalle;


    public ManejadorListarPagoDetalle(DaoPagoDetalle daoPagoDetalle) {
        this.daoPagoDetalle = daoPagoDetalle;
    }

    public List<DtoPagoDetalle> ejecutar(Long idPago){
        return this.daoPagoDetalle.listarPagoDetallePorIdPago(idPago);
    }
}
