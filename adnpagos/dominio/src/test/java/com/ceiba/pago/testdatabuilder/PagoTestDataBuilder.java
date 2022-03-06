package com.ceiba.pago.testdatabuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

public class PagoTestDataBuilder {
	private Long id;
	private LocalDateTime fechaPago;
	private String identificacionCliente;
	private Double subTotal;
	private Double total;
	private List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders;
	
	public PagoTestDataBuilder() {
		id=1L;
		fechaPago= LocalDateTime.parse("2022-03-28T15:00:00.104");
		identificacionCliente="1717213183";
		subTotal=0.0;
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
	public PagoTestDataBuilder conTotal(Double total) {
		this.total=total;
		return this;
	}
	
	public PagoTestDataBuilder conServicioElectricoTestDataBuilders(List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders) {
		this.servicioElectricoTestDataBuilders=servicioElectricoTestDataBuilders;
		return this;
	}
	
	public Pago build() {
		return new Pago(id, fechaPago, identificacionCliente, subTotal, total, convertirAServicioElectrico(servicioElectricoTestDataBuilders));
	}

	private List<ServicioElectrico>  convertirAServicioElectrico(List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders){
		if(servicioElectricoTestDataBuilders!=null){
			ArrayList<ServicioElectrico> servicioElectricos= new ArrayList<>();
			for (ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder: servicioElectricoTestDataBuilders) {
				servicioElectricos.add(servicioElectricoTestDataBuilder.build());
			}
			return  servicioElectricos;
		}
		return null;
	}

}
