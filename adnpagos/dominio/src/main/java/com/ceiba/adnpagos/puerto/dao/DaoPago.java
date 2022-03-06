package com.ceiba.adnpagos.puerto.dao;

import com.ceiba.adnpagos.modelo.dto.DtoPago;

import java.util.List;

public interface DaoPago {
    /**
     * Permite listar pagos
     * @return los pagos
     */
    public List<DtoPago> listar ();
}
