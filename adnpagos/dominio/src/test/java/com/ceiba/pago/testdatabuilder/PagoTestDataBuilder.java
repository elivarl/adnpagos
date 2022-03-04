package com.ceiba.pago.testdatabuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;

public class PagoTestDataBuilder {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double subTotal;
	private String porcentajeDescuento;
	private Double valorDescuento;
	private Double total;
	private List<PagoDetalle> pagosDetalle;
	
	public PagoTestDataBuilder() {
		fechaPago= LocalDateTime.now();	
		identificacionCliente="1717213183";
		subTotal=0.0;
		porcentajeDescuento="";
		valorDescuento=0.0;
		total=0.0;
	}
	
	public PagoTestDataBuilder conId(Long id) {
		this.id=id;
		return this;
	}
	
	public PagoTestDataBuilder conFechaPago(LocalDateTime fechaPago) {
		this.fechaPago=fechaPago;
		return this;
	}
	
	public PagoTestDataBuilder conIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente=identificacionCliente;
		return this;
	}
	
	public PagoTestDataBuilder conSubTotal(Double subTotal) {
		this.subTotal=subTotal;
		return this;
	}
	
	public PagoTestDataBuilder conPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento=porcentajeDescuento;
		return this;
	}
	
	public PagoTestDataBuilder conValorDescuento(Double valorDescuento) {
		this.valorDescuento=valorDescuento;
		return this;
	}
	
	public PagoTestDataBuilder conTotal(Double total) {
		this.total=total;
		return this;
	}
	
	public PagoTestDataBuilder conPagoDetalles(List<PagoDetalle> pagosDetalle) {
		this.pagosDetalle=pagosDetalle;
		return this;
	}
	
	public Pago build() {
		return new Pago(id, fechaPago, identificacionCliente, subTotal, porcentajeDescuento, valorDescuento, total, pagosDetalle);
	}

}
