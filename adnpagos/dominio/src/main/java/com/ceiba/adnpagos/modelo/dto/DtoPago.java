package com.ceiba.adnpagos.modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DtoPago {
    private Long id;
    private LocalDateTime fechaPago;
    private String identificacionCliente;
    private Double subTotal;
    private Double total;
    private String porcentajeDescuentoRecargo;
    private Double valorDescuentoRecargo;
    private List<DtoServicioElectrico> dtoServiciosPago;

    public DtoPago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double subTotal,Double total,String porcentajeDescuentoRecargo ,Double valorDescuentoRecargo) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.identificacionCliente = identificacionCliente;
        this.subTotal=subTotal;
        this.total = total;
        this.porcentajeDescuentoRecargo=porcentajeDescuentoRecargo;
        this.valorDescuentoRecargo=valorDescuentoRecargo;
    }

    public DtoPago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double subTotal,Double total,String porcentajeDescuentoRecargo ,Double valorDescuentoRecargo, List<ServicioElectrico> servicioElectricos) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.identificacionCliente = identificacionCliente;
        this.subTotal=subTotal;
        this.total = total;
        this.porcentajeDescuentoRecargo=porcentajeDescuentoRecargo;
        this.valorDescuentoRecargo=valorDescuentoRecargo;
        this.dtoServiciosPago = convertirServicioElectricoADtoservicioElectrico(servicioElectricos);
    }

    private List<DtoServicioElectrico> convertirServicioElectricoADtoservicioElectrico(List<ServicioElectrico> servicioElectricos) {
        this.dtoServiciosPago =new ArrayList<>();
        for (ServicioElectrico servicioElectrico : servicioElectricos
        ) {
            this.dtoServiciosPago.add(new DtoServicioElectrico(servicioElectrico.getId(), servicioElectrico.getNumeroServicio(),servicioElectrico.getIdentificacionCliente(), servicioElectrico.getNombreCliente(), servicioElectrico.getMes(), servicioElectrico.getFechaMaximaPago(), servicioElectrico.getValor(), servicioElectrico.isEstado()));
        }
        return  this.dtoServiciosPago;
    }
}
