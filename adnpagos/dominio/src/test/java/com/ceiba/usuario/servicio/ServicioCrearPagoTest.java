package com.ceiba.usuario.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.ReglaPago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.usuario.servicio.testdatabuilder.PagoDetalleTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.PagoTestDataBuilder;

public class ServicioCrearPagoTest {

	@Test
	void crearPagoDiaSabado() {
		// arrange
		LocalDateTime diaPagoSabado = LocalDateTime.parse("2022-03-05T15:00:00.104");
		LocalDateTime diaPagoLunesCreada = LocalDateTime.parse("2022-03-07T15:00:00.104");
		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		// act
		LocalDateTime fechaServicioCrearPagoSabado = servicioCrearPago.obtenerFechaLaboralPago(diaPagoSabado);

		// asert
		assertEquals(diaPagoLunesCreada, fechaServicioCrearPagoSabado);

	}

	@Test
	void crearPagoDiaDomingo() {
		// arrange
		LocalDateTime diaPagoDomingo = LocalDateTime.parse("2022-03-06T15:00:00.104");
		LocalDateTime diaPagoLunesCreada = LocalDateTime.parse("2022-03-07T15:00:00.104");

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		// act
		LocalDateTime fechaServicioCrearPagoDiaDomingo = servicioCrearPago.obtenerFechaLaboralPago(diaPagoDomingo);

		// asert
		assertEquals(diaPagoLunesCreada, fechaServicioCrearPagoDiaDomingo);

	}

	@Test
	void crearPagoDiaNormal() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-02T15:00:00.104");
		LocalDateTime diaPagoNormalCreada = LocalDateTime.parse("2022-03-02T15:00:00.104");

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		// act
		LocalDateTime fechaServicioCrearPagoDiaNormal = servicioCrearPago.obtenerFechaLaboralPago(diaPagoNormal);

		// asert
		assertEquals(diaPagoNormalCreada, fechaServicioCrearPagoDiaNormal);

	}

	@Test
	void aplicarReglaNegocioDiezDiasAntes() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-02T15:00:00.104");

		ServicioElectrico servicioElectrico = new ServicioElectrico(1L, "1", "1717213183", "Elivar Oswaldo Largo",
				"Abril", LocalDateTime.parse("2022-03-18T15:00:00.104"), 90.00, false, null);

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		List<PagoDetalleTestDataBuilder> detalleTestDataBuilders = new ArrayList<PagoDetalleTestDataBuilder>();
		detalleTestDataBuilders
				.add(new PagoDetalleTestDataBuilder().conIdServicio(1L).conValor(servicioElectrico.getValor()));

		String porcentajeDescuento = "8";
		Double valorDescuento = 7.2;

		PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conFechaPago(diaPagoNormal)
				.conPagoDetalles(detalleTestDataBuilders);

		// act
		Pago pago = servicioCrearPago.aplicarReglasPago(1L, pagoTestDataBuilder.build(), servicioElectrico);

		// asert
		assertEquals(porcentajeDescuento, pago.getPorcentajeDescuento());
		assertEquals(valorDescuento, pago.getValorDescuento());

	}

	@Test
	void aplicarReglaNegocioTresDiasAntes() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-02T15:00:00.104");

		ServicioElectrico servicioElectrico = new ServicioElectrico(1L, "1", "1717213183", "Elivar Oswaldo Largo",
				"Abril", LocalDateTime.parse("2022-03-04T15:00:00.104"), 90.00, false, null);

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		List<PagoDetalleTestDataBuilder> detalleTestDataBuilders = new ArrayList<PagoDetalleTestDataBuilder>();
		detalleTestDataBuilders
				.add(new PagoDetalleTestDataBuilder().conIdServicio(1L).conValor(servicioElectrico.getValor()));

		String porcentajeDescuento = "0";
		Double valorDescuento = 0.0;

		PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conFechaPago(diaPagoNormal)
				.conPagoDetalles(detalleTestDataBuilders);

		// act
		Pago pago = servicioCrearPago.aplicarReglasPago(1L, pagoTestDataBuilder.build(), servicioElectrico);

		// asert
		assertEquals(porcentajeDescuento, pago.getPorcentajeDescuento());
		assertEquals(valorDescuento, pago.getValorDescuento());

	}

	@Test
	void aplicarReglaNegocioPagosMayoresFechaPago() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-25T15:00:00.104");

		ServicioElectrico servicioElectrico = new ServicioElectrico(1L, "1", "1717213183", "Elivar Oswaldo Largo",
				"Abril", LocalDateTime.parse("2022-03-26T15:00:00.104"), 90.00, false, null);

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		List<PagoDetalleTestDataBuilder> detalleTestDataBuilders = new ArrayList<PagoDetalleTestDataBuilder>();
		detalleTestDataBuilders
				.add(new PagoDetalleTestDataBuilder().conIdServicio(1L).conValor(servicioElectrico.getValor()));

		String porcentajeRecargo = "10";
		Double valorDescuento = 9.00;

		PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conFechaPago(diaPagoNormal)
				.conPagoDetalles(detalleTestDataBuilders);

		// act
		Pago pago = servicioCrearPago.aplicarReglasPago(1L, pagoTestDataBuilder.build(), servicioElectrico);

		// asert
		assertEquals(porcentajeRecargo, pago.getPorcentajeDescuento());
		assertEquals(valorDescuento, pago.getValorDescuento());

	}

	@Test
	void calcularDiasLaborables() {
		// arrange
		LocalDateTime diaPago = LocalDateTime.parse("2022-03-04T15:00:00.104");

		LocalDateTime diaPagoMaximo = LocalDateTime.parse("2022-03-11T15:00:00.104");

		Long diasCalculado = 5L;

		RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
		DaoServicioElectrico daoServicio = Mockito.mock(DaoServicioElectrico.class);
		RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
		ReglaPago reglaPago = Mockito.mock(ReglaPago.class);

		ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago, daoServicio,
				repositorioServicioElectrico, reglaPago);

		// act
		Long diasLaborables = servicioCrearPago.calcularDias(diaPago, diaPagoMaximo);

		// asert
		assertEquals(diasCalculado, diasLaborables);

	}

}
