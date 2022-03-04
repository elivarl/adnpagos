package com.ceiba.usuario.servicio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import com.ceiba.adnpagos.modelo.entidad.NoLaboral;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.ReglaPago;
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
		pago.setFechaPago(obtenerFechaLaboralPago(pago.getFechaPago()));
		Long idPago = this.repositorioPago.crear(pago);

		// guarda lista detalles
		guardarListaDetalles(pago.getPagosDetalle(), idPago);

		// actualizar pago valor total
		actualizarSubTotalPago(idPago, pago);

		ServicioElectrico servicioElectrico = obtenerServicioPorId(
				p -> p.getId() == pago.getPagosDetalle().get(0).getIdServicio());

		// aplicar reglas
		repositorioPago.actualizar(aplicarReglasPago(idPago, pago, servicioElectrico));

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
				return servicio;
			}
		}
		return new ServicioElectrico(1L, "1717213183", "Elivar Largo", "Enero","Elivar",LocalDateTime.parse("2022-03-26T15:00:00.104") , 10.0,false, null);
	}

	private double sumarSubTotalDetalles(List<PagoDetalle> detalles) {
		return detalles.stream().mapToDouble(d -> d.getValor()).sum();
	}

	private void actualizarEstadoServicio(List<PagoDetalle> detalles) {
		for (PagoDetalle pagoDetalle : detalles) {
			ServicioElectrico sv = new ServicioElectrico(null, null, null, null, null, null, null, false, null);
			sv = obtenerServicioPorId(servicio -> servicio.getId() == pagoDetalle.getIdServicio());
			sv.setEstado(true);
			repositorioServicioElectrico.actualizar(sv);
		}

	}

	private void actualizarSubTotalPago(Long idPago, Pago pago) {
		pago.setId(idPago);
		pago.setSubTotal(sumarSubTotalDetalles(pago.getPagosDetalle()));
		repositorioPago.actualizar(pago);
	}

	public LocalDateTime obtenerFechaLaboralPago(LocalDateTime fechaPago) {
		if (fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())
				|| fechaPago.getDayOfWeek().name().equals(NoLaboral.SUNDAY.toString())) {

			if (fechaPago.getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())) {
				return fechaPago.plusDays(2);
			} else {
				return fechaPago.plusDays(1);
			}

		}
		return fechaPago;
	}

	public Pago aplicarReglasPago(Long idPago, Pago pago, ServicioElectrico servicioElectrico) {
		if (pago.getPagosDetalle().size() < 2) {
			pago.setId(idPago);
			pago.setSubTotal(sumarSubTotalDetalles(pago.getPagosDetalle()));
			long dias = calcularDias(pago.getFechaPago(), servicioElectrico.getFechaMaximaPago());

			long porcentaheDescuento = obtenerPorcentajeDescuento(dias);
			double valorDescuento = calcularValorDescuento(pago.getSubTotal(), porcentaheDescuento);
			double totalPago = calcularTotalPago(pago.getSubTotal(), porcentaheDescuento, dias);
			pago.setValorDescuento(valorDescuento);
			pago.setPorcentajeDescuento(String.valueOf(porcentaheDescuento));
			pago.setTotal(totalPago);
			return pago;
		}
		return pago;
	}

	public long calcularDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		Duration d = Duration.between(fechaInicial, fechaFinal);
		LocalDateTime fi = fechaInicial;
		long diasLaborales = 0;
		for (int i = 1; i <= d.toDays(); i++) {
			String diaLaborable = fi.plusDays(i).getDayOfWeek().name();
			if (!diaLaborable.equals("SATURDAY")) {
				if (!diaLaborable.equals("SUNDAY")) {
					diasLaborales++;
				}
			}

		}
		return diasLaborales;
	}

	private double calcularValorDescuento(Double subtotal, long porcentajeDescuento) {
		return (subtotal * porcentajeDescuento) / 100;
	}

	private long obtenerPorcentajeDescuento(long dias) {
		if (dias > 3) {
			return 8;
		} else if (dias <= 3 && dias >= 1) {
			return 0;
		}
		return 10;
	}

	private double calcularTotalPago(Double subtotal, long porcentajeDescuento, long dias) {
		if (dias > 0) {
			return subtotal - ((subtotal * porcentajeDescuento) / 100);
		}
		return subtotal + ((subtotal * porcentajeDescuento) / 100);
	}

}
