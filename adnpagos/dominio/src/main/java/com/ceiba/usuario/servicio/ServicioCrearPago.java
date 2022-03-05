package com.ceiba.usuario.servicio;

import java.util.List;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;

public class ServicioCrearPago {

	private final RepositorioPago repositorioPago;

	private final DaoServicioElectrico daoServicio;

	private final RepositorioServicioElectrico repositorioServicioElectrico;

	public ServicioCrearPago(RepositorioPago repositorioPago, DaoServicioElectrico daoServicio,
			RepositorioServicioElectrico repositorioServicioElectrico) {
		this.repositorioPago = repositorioPago;
		this.daoServicio = daoServicio;
		this.repositorioServicioElectrico = repositorioServicioElectrico;
	}

	public Long ejecutar(Pago pago) {
		Long idPago = this.repositorioPago.crear(pago);
		// guarda lista detalles
		//crearDetalles(pago.getPagosDetalle(), idPago);
		// actualiza estado pago
		//actualizarEstadoServicio(pago.getPagosDetalle());
		return idPago;
	}

	private void crearDetalles(List<PagoDetalle> detalles, Long idPago) {
		for (PagoDetalle pagoDetalle : detalles) {
			pagoDetalle.setIdPago(idPago);
			crearPagoDetalle(pagoDetalle);
		}
	}

	private void crearPagoDetalle(PagoDetalle pagoDetalle) {
		repositorioPago.crearPagoDetalle(pagoDetalle);
	}

}
