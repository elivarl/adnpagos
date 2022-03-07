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
    private Double valorTotal;
    private List<DtoServicioElectrico> dtoServiciosPago;

    public DtoPago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double valorTotal) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.identificacionCliente = identificacionCliente;
        this.valorTotal = valorTotal;
    }

    public DtoPago(Long id, LocalDateTime fechaPago, String identificacionCliente, Double valorTotal, List<ServicioElectrico> servicioElectricos) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.identificacionCliente = identificacionCliente;
        this.valorTotal = valorTotal;
        this.dtoServiciosPago = convertirServicioElectricoADtoservicioElectrico(servicioElectricos);
    }

    public List<DtoServicioElectrico> convertirServicioElectricoADtoservicioElectrico(List<ServicioElectrico> servicioElectricos) {
        this.dtoServiciosPago =new ArrayList<>();
        for (ServicioElectrico servicioElectrico : servicioElectricos
        ) {
            this.dtoServiciosPago.add(new DtoServicioElectrico(servicioElectrico.getId(), servicioElectrico.getNumeroServicio(),servicioElectrico.getIdentificacionCliente(), servicioElectrico.getNombreCliente(), servicioElectrico.getMes(), servicioElectrico.getFechaMaximaPago(), servicioElectrico.getValor(), servicioElectrico.isEstado(),servicioElectrico.getFechaCreacion()));
        }
        return  this.dtoServiciosPago;
    }
}
