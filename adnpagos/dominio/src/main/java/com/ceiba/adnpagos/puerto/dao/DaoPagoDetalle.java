package com.ceiba.adnpagos.puerto.dao;

import com.ceiba.adnpagos.modelo.dto.DtoPagoDetalle;

import java.util.List;

public interface DaoPagoDetalle {
    /**
     * Permite listar el detalle de un pago por id del pago
     * @return los detalles
     */
    public List<DtoPagoDetalle> listarPagoDetallePorIdPago(Long idPago);
}
