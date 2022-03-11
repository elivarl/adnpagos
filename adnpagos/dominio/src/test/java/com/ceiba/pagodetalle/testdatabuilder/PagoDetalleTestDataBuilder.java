package com.ceiba.pagodetalle.testdatabuilder;

import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

public class PagoDetalleTestDataBuilder {

    private Long id;
    private String descripcion;
    private Double valor;
    private Long idServicio;
    private Long idPago;

    public PagoDetalleTestDataBuilder() {
        id=1L;
        descripcion="Pago mes Enero";
        valor=10.0;
        idServicio=1L;
        idPago=1L;
    }

    public PagoDetalleTestDataBuilder conId(Long id) {
        this.id=id;
        return this;
    }
    public PagoDetalleTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion=descripcion;
        return this;
    }

    public PagoDetalleTestDataBuilder conValor(Double valor) {
        this.valor=valor;
        return this;
    }
    public PagoDetalleTestDataBuilder conIdServicio(Long idServicio) {
        this.idServicio=idServicio;
        return this;
    }
    public PagoDetalleTestDataBuilder conIdPago(Long idPago) {
        this.idPago=idPago;
        return this;
    }

    public PagoDetalle build(){
        return new PagoDetalle(id, descripcion, valor, idServicio);
    }
}
