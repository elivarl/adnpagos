package com.ceiba.pago.entidad;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.pago.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.pago.testdatabuilder.ServicioElectricoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidarReglasPagoTest {

    private ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder;
    private List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders;

    @BeforeEach
    public void inicializarDatosServiciosElectricosParaUnPago() {
        //objetos Servicio electrico test data builder
        servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

    }

    @Test
    void crearPagoDiaSabado() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_SABADO = LocalDateTime.parse("2022-03-05T15:00:00.104");
        LocalDateTime DEBERIA_CREAR_PAGO_CON_FECHA_DIA_LUNES = LocalDateTime.parse("2022-03-07T15:00:00.104");

        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_SABADO).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();


        //assert
        assertEquals(DEBERIA_CREAR_PAGO_CON_FECHA_DIA_LUNES, pago.getFechaPago());

    }

    @Test
    void crearPagoDiaDomingo() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_DOMINGO = LocalDateTime.parse("2022-03-06T15:00:00.104");
        LocalDateTime DEBERIA_CREAR_PAGO_CON_FECHA_DIA_LUNES = LocalDateTime.parse("2022-03-07T15:00:00.104");


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_DOMINGO).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();

        //assert
        assertEquals(DEBERIA_CREAR_PAGO_CON_FECHA_DIA_LUNES, pago.getFechaPago());

    }

    @Test
    void crearPagoDiaLunesAViernes() {

        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES = LocalDateTime.parse("2022-03-02T15:00:00.104");
        LocalDateTime DEBERIA_CREAR_CON_FECHA_MISMO_DIA_PAGO = LocalDateTime.parse("2022-03-02T15:00:00.104");

        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setReglapFechaPagoLaboral();

        //assert
        assertEquals(DEBERIA_CREAR_CON_FECHA_MISMO_DIA_PAGO, pago.getFechaPago());

    }

    @Test
    void aplicarReglaNegocioDiezDiasAntesConUnPagoDetalle() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES = LocalDateTime.parse("2022-03-02T15:00:00.104");

        final Double DEBERIA_CALCULAR_TOTAL_PAGO = 82.80;
        final String DEBERIA_OBTENER_PORCENTAJE_DESCUENTO = "8";
        final Double DEBERIA_OBTENER_VALOR_DESCUENTO = 7.2;

        //objeto servicio electrico test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-18T15:00:00.104")).conValor(90.0);
        //array test data builder
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conSubTotal(90.0).conTotal(90.0).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();

        // asert
        assertEquals(DEBERIA_OBTENER_PORCENTAJE_DESCUENTO, pago.getPorcentajeDescuentoRecargo());
        assertEquals(DEBERIA_OBTENER_VALOR_DESCUENTO, pago.getValorDescuentoRecargo());
        assertEquals(DEBERIA_CALCULAR_TOTAL_PAGO, pago.getTotal());

    }

    @Test
    void aplicarReglaNegocioTresDiasAntesConUnPagoDetalle() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES = LocalDateTime.parse("2022-03-02T15:00:00.104");

        final Double DEBERIA_CALCULAR_TOTAL_PAGO = 90.90;
        final String DEBERIA_OBTENER_PORCENTAJE_DESCUENTO = "0";
        final Double DEBERIA_OBTENER_VALOR_DESCUENTO = 0.0;


        //objeto servicio electrico test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-04T15:00:00.104")).conValor(90.9);
        //array test data builder
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conSubTotal(90.9).conTotal(90.9).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();

        // asert
        assertEquals(DEBERIA_OBTENER_PORCENTAJE_DESCUENTO, pago.getPorcentajeDescuentoRecargo());
        assertEquals(DEBERIA_OBTENER_VALOR_DESCUENTO, pago.getValorDescuentoRecargo());
        assertEquals(DEBERIA_CALCULAR_TOTAL_PAGO, pago.getTotal());
    }

    @Test
    void aplicarReglaNegocioPagosMayoresFechaPagoConUnPagoDetalle() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES = LocalDateTime.parse("2022-03-25T15:00:00.104");
        final Double DEBERIA_CALCULAR_TOTAL_PAGO = 99.00;
        final String DEBERIA_OBTENER_PORCENTAJE_DESCUENTO_RECARGO = "10";
        final Double DEBERIA_OBTENER_VALOR_DESCUENTO_RECARGO = 9.0;

        //objeto servicio electrico test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(90.0);
        //array test data builder
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conSubTotal(90.0).conTotal(90.0).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();

        // asert
        assertEquals(DEBERIA_OBTENER_PORCENTAJE_DESCUENTO_RECARGO, pago.getPorcentajeDescuentoRecargo());
        assertEquals(DEBERIA_OBTENER_VALOR_DESCUENTO_RECARGO, pago.getValorDescuentoRecargo());
        assertEquals(DEBERIA_CALCULAR_TOTAL_PAGO, pago.getTotal());
    }

    @Test
    void crearUnPagoConVariosPagoDetalleNoAplicaReglas() {
        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES = LocalDateTime.parse("2022-03-25T15:00:00.104");

        final Double DEBERIA_CALCULAR_TOTAL_PAGO=110.00;
        final String DEBERIA_OBTENER_PORCENTAJE_DESCUENTO_RECARGO = "0";
        final Double DEBERIA_OBTENER_VALOR_DESCUENTO = 0.0;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder1 = new ServicioElectricoTestDataBuilder().
                conId(1L).conNumeroServicio("1").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(90.00);

        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder2 = new ServicioElectricoTestDataBuilder().
                conId(2L).conNumeroServicio("2").conIdentificacionCliente("1717213183").conNombreCliente("Elivar Oswaldo Largo")
                .conMes("Enero").conFechaMaximaPago(LocalDateTime.parse("2022-03-26T15:00:00.104")).conValor(20.00);

        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder1);
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder2);


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conSubTotal(110.0).conTotal(110.0).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        // act
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
        // asert
        assertEquals(DEBERIA_OBTENER_PORCENTAJE_DESCUENTO_RECARGO, pago.getPorcentajeDescuentoRecargo());
        assertEquals(DEBERIA_OBTENER_VALOR_DESCUENTO, pago.getValorDescuentoRecargo());
        assertEquals(DEBERIA_CALCULAR_TOTAL_PAGO, pago.getTotal());
    }

    @Test
    void crearPagoDiaNormalConActualizarServicio() {

        // arrange
        LocalDateTime CON_FECHA_PAGO_DIA_LUNES_A_VIERNES  = LocalDateTime.parse("2022-03-02T15:00:00.104");
        LocalDateTime DEBERIA_CREAR_CON_FECHA_MISMO_DIA_PAGO = LocalDateTime.parse("2022-03-02T15:00:00.104");

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);


        Pago pago = new PagoTestDataBuilder().conFechaPago(CON_FECHA_PAGO_DIA_LUNES_A_VIERNES).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //act
        pago.setEstadoServicio();

        //assert
        assertEquals(DEBERIA_CREAR_CON_FECHA_MISMO_DIA_PAGO, pago.getFechaPago());
        assertEquals(true, pago.getPagoServicios().get(0).isEstado());

    }


}
