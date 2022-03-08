package com.ceiba.pago.entidad;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.pago.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.pago.testdatabuilder.ServicioElectricoTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidarReglasPagoTest {

	@Test
	void crearPagoDiaSabado() {
		// arrange
        LocalDateTime FECHA_PAGO_DIA_SABADO= LocalDateTime.parse("2022-03-05T15:00:00.104");
        LocalDateTime diaPagoLunesDeberiaCrear = LocalDateTime.parse("2022-03-07T15:00:00.104");

        ServicioElectrico servicioElectrico= new ServicioElectricoTestDataBuilder().build();
        List<ServicioElectrico> servicioElectricos= new ArrayList<>();
        servicioElectricos.add(servicioElectrico);

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

        Pago pago = new PagoTestDataBuilder().conFechaPago(FECHA_PAGO_DIA_SABADO).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();


        //assert
        assertEquals(diaPagoLunesDeberiaCrear, pago.getFechaPago());

	}

	@Test
	void crearPagoDiaDomingo() {
		// arrange
        LocalDateTime FECHA_PAGO_DIA_DOMINGO= LocalDateTime.parse("2022-03-06T15:00:00.104");
        LocalDateTime diaPagoLunesDeberiaCrear = LocalDateTime.parse("2022-03-07T15:00:00.104");

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(FECHA_PAGO_DIA_DOMINGO).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();

        //assert
        assertEquals(diaPagoLunesDeberiaCrear, pago.getFechaPago());

	}

	@Test
	void crearPagoDiaNormal() {

        // arrange
        LocalDateTime FECHA_PAGO_DIA_NORMAL= LocalDateTime.parse("2022-03-02T15:00:00.104");
        LocalDateTime diaPagoLunesDeberiaCrear = LocalDateTime.parse("2022-03-02T15:00:00.104");

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(FECHA_PAGO_DIA_NORMAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();

        //assert
        assertEquals(diaPagoLunesDeberiaCrear, pago.getFechaPago());

	}

	@Test
	void aplicarReglaNegocioDiezDiasAntesConUnPagoDetalle() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-02T15:00:00.104");
        final Double SUBTOTAL=90.0;
        final Double TOTAL=82.80;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-18T15:00:00.104")).conValor(90.00).
                        conFechaCreacion(LocalDateTime.now());

        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

		String porcentajeDescuento = "8";
		Double valorDescuento = 7.2;

        Pago pago = new PagoTestDataBuilder().conFechaPago(diaPagoNormal).conSubTotal(SUBTOTAL).conTotal(TOTAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
		pago.setAplicarReglaPorcentajeDescuentoRecargo();

		// asert
		assertEquals(porcentajeDescuento,  pago.getPorcentajeDescuentoRecargo());
		assertEquals(valorDescuento, pago.getValorDescuentoRecargo());
        assertEquals(TOTAL, pago.getTotal());

	}

	@Test
	void aplicarReglaNegocioTresDiasAntesConUnPagoDetalle() {
        // arrange
        LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-02T15:00:00.104");
        final Double SUBTOTAL=90.0;
        final Double TOTAL=90.0;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-04T15:00:00.104")).conValor(90.00).
                        conFechaCreacion(LocalDateTime.now());

        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);
		String porcentajeDescuento = "0";
		Double valorDescuento = 0.0;

        Pago pago = new PagoTestDataBuilder().conFechaPago(diaPagoNormal).conSubTotal(SUBTOTAL).conTotal(TOTAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();

        // asert
		assertEquals(porcentajeDescuento, pago.getPorcentajeDescuentoRecargo());
		assertEquals(valorDescuento, pago.getValorDescuentoRecargo());
        assertEquals(TOTAL, pago.getTotal());
	}

	@Test
	void aplicarReglaNegocioPagosMayoresFechaPagoConUnPagoDetalle() {
		// arrange
		LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-25T15:00:00.104");

        final Double SUBTOTAL=90.0;
        final Double TOTAL=99.0;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(90.00).
                        conFechaCreacion(LocalDateTime.now());

        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

		String porcentajeRecargo = "10";
		Double valorDescuento = 9.00;

        Pago pago = new PagoTestDataBuilder().conFechaPago(diaPagoNormal).conSubTotal(SUBTOTAL).conTotal(TOTAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
		// asert
		assertEquals(porcentajeRecargo,  pago.getPorcentajeDescuentoRecargo());
		assertEquals(valorDescuento, pago.getValorDescuentoRecargo());
        assertEquals(TOTAL, pago.getTotal());
	}

    @Test
    void crearUnPagoConVariosPagoDetalleNoAplicaReglas() {
        // arrange
        LocalDateTime diaPagoNormal = LocalDateTime.parse("2022-03-25T15:00:00.104");

        final Double SUBTOTAL=110.0;
        final Double TOTAL=110.0;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder1 = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(90.00).
                        conFechaCreacion(LocalDateTime.now());

        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder2 = new ServicioElectricoTestDataBuilder().
                conId(2L).conNumeroServicio("2").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(20.00).
                        conFechaCreacion(LocalDateTime.now());

        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder1);
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder2);

        String porcentajeRecargo = "0";
        Double valorDescuento = 0.0;

        Pago pago = new PagoTestDataBuilder().conFechaPago(diaPagoNormal).conSubTotal(SUBTOTAL).conTotal(TOTAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
        // asert
        assertEquals(porcentajeRecargo,  pago.getPorcentajeDescuentoRecargo());
        assertEquals(valorDescuento, pago.getValorDescuentoRecargo());
        assertEquals(TOTAL, pago.getTotal());
    }

    @Test
    void crearPagoDiaNormalConActualizarServicio() {

        // arrange
        LocalDateTime FECHA_PAGO_DIA_NORMAL= LocalDateTime.parse("2022-03-02T15:00:00.104");
        LocalDateTime diaPagoLunesDeberiaCrear = LocalDateTime.parse("2022-03-02T15:00:00.104");

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders= new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(FECHA_PAGO_DIA_NORMAL).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setEstadoServicio();

        //assert
        assertEquals(diaPagoLunesDeberiaCrear, pago.getFechaPago());
        assertEquals(true,pago.getPagoServicios().get(0).isEstado());

    }





}
