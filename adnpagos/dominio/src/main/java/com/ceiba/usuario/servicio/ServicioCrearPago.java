package com.ceiba.usuario.servicio;

import java.util.List;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;

public class ServicioCrearPago {

	private final RepositorioPago repositorioPago;

	public ServicioCrearPago(RepositorioPago repositorioPago) {
		this.repositorioPago = repositorioPago;
	}

	public Long ejecutar(Pago pago) {
		Long idPago = this.repositorioPago.crear(pago);
		// guarda lista detalles
		crearDetalles(pago.getPagoDetalles(), idPago);
		crearServicioElectrico(pago.getPagoServicios());
		return idPago;
	}

	private void crearDetalles(List<PagoDetalle> detalles, Long idPago) {
		for (PagoDetalle pagoDetalle : detalles) {
			pagoDetalle.setIdPago(idPago);
			crearPagoDetalle(pagoDetalle);
		}
	}

	public Long crearPagoDetalle(PagoDetalle pagoDetalle) {
		return repositorioPago.crearPagoDetalle(pagoDetalle);
	}

	public void actualizarServicioElectrico(ServicioElectrico servicioElectrico){
		repositorioPago.actualizarServivioElectrico(servicioElectrico);
	}

	private void crearServicioElectrico(List<ServicioElectrico> servicioElectricos) {
		for (ServicioElectrico servicioElectrico : servicioElectricos) {
			actualizarServicioElectrico(servicioElectrico);
		}
	}

}
