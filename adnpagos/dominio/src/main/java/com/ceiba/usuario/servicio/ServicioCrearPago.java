package com.ceiba.usuario.servicio;

import java.util.function.Predicate;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;

public class ServicioCrearPago {
	
	private final RepositorioPago repositorioPago;
	
	private final DaoServicioElectrico daoServicio;
	
	public ServicioCrearPago(RepositorioPago repositorioPago, DaoServicioElectrico daoServicio) {
		this.repositorioPago=repositorioPago;
		this.daoServicio=daoServicio;
	}
	
	public Long ejecutar (Pago pago) {
		Long idPago=this.repositorioPago.crear(pago);
		System.out.println("Id pago: "+idPago);	
		
		
		for (PagoDetalle pagoDetalle: pago.getPagosDetalle()) {
			
			pagoDetalle.setIdPago(idPago);
			pagoDetalle.setDescripcion("Pago mes: "+obtenerMesPago(servicio-> servicio.getId()==pagoDetalle.getIdServicio()).getMesPago());
			System.out.println("Pago detalle idPago: "+pagoDetalle.getIdPago());
			System.out.println("Pago detalle idServicio: "+pagoDetalle.getIdServicio());
			crearPagoDetalle(pagoDetalle);
		}
		return idPago;
	}
	
	//crea un detalle para un pago
	private void crearPagoDetalle(PagoDetalle pagoDetalle) {
		repositorioPago.crearPagoDetalle(pagoDetalle);
	}
	
	//obtener el mes de pago para la descripcion del detalle
	private ServicioElectrico obtenerMesPago(Predicate<ServicioElectrico> eval) {
		for (ServicioElectrico servicio :daoServicio.listarServicio()) {
			if(eval.test(servicio)) {
				System.out.println("serv..."+servicio.getMesPago());
				return servicio;
			}
		}
		return null;
	}

}
