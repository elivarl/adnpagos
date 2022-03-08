package com.ceiba.adnpagos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPagoDetalle {
    private String descripcion;
    private Double valor;
}
