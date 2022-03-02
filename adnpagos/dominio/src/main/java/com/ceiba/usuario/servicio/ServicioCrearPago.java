package com.ceiba.usuario.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import com.ceiba.adnpagos.modelo.entidad.NoLaboral;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
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
		System.out.println("Id pago: " + idPago);
		pago.setFechaPago(obtenerFechaLaboralPago());
		
		// guarda lista detalles
		guardarListaDetalles(pago.getPagosDetalle(), idPago);

		// actualizar pago valor total
		actualizarSubTotalPago(idPago, pago);

		// actualiza estado pago
		actualizarEstadoServicio(pago.getPagosDetalle());

		return idPago;
	}

	// crea un detalle para un pago
	private void crearPagoDetalle(PagoDetalle pagoDetalle) {
		repositorioPago.crearPagoDetalle(pagoDetalle);
	}

	private void guardarListaDetalles(List<PagoDetalle> detalles, Long idPago) {
		for (PagoDetalle pagoDetalle : detalles) {
			pagoDetalle.setIdPago(idPago);
			pagoDetalle.setDescripcion("Pago mes: "
					+ obtenerServicioPorId(servicio -> servicio.getId() == pagoDetalle.getIdServicio()).getMesPago());
			pagoDetalle.setValor(
					obtenerServicioPorId(servicio -> servicio.getId() == pagoDetalle.getIdServicio()).getValor());
			System.out.println("Pago detalle idPago: " + pagoDetalle.getIdPago());
			System.out.println("Pago detalle idServicio: " + pagoDetalle.getIdServicio());
			crearPagoDetalle(pagoDetalle);
		}

	}

	// obtener el servicio para la descripcion del detalle
	private ServicioElectrico obtenerServicioPorId(Predicate<ServicioElectrico> eval) {
		for (ServicioElectrico servicio : daoServicio.listarServicio()) {
			if (eval.test(servicio)) {
				System.out.println("serv..." + servicio.getMesPago());
				return servicio;
			}
		}
		return null;
	}

	private double sumarSubTotalDetalles(List<PagoDetalle> detalles) {
		return detalles.stream().mapToDouble(d -> d.getValor()).sum();
	}

	private void actualizarEstadoServicio(List<PagoDetalle> detalles) {
		for (PagoDetalle pagoDetalle : detalles) {
			System.out.println("det..");
			ServicioElectrico sv = new ServicioElectrico(null, null, null, null, null, null, null, false, null);
			sv = obtenerServicioPorId(servicio -> servicio.getId() == pagoDetalle.getIdServicio());
			System.out.println("sv: " + sv.isEstado());
			sv.setEstado(true);
			repositorioServicioElectrico.actualizar(sv);
		}

	}

	private void actualizarSubTotalPago(Long idPago, Pago pago) {
		pago.setId(idPago);
		pago.setSubTotal(sumarSubTotalDetalles(pago.getPagosDetalle()));
		repositorioPago.actualizar(pago);
	}
	
	private LocalDateTime obtenerFechaLaboralPago() {
		if (LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())||LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SUNDAY.toString())) {
			if(LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())) {
				return LocalDateTime.now().plusDays(2);
			}else {
				return LocalDateTime.now().plusDays(1);
			}
			
		}
		return LocalDateTime.now();
	}

}
