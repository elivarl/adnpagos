package com.ceiba.pago.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.pago.testdatabuilder.ServicioElectricoTestDataBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagoTest {

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
    @DisplayName("Deberia crear correctamente un pago")
    void deberiaCrearCorrectamenteElPago() {
        // arrange
        final long ID = 1L;
        LocalDateTime FECHA_PAGO = LocalDateTime.parse("2022-03-28T15:00:00.104");
        final String IDENTIFICACION_CLIENTE = "1717213183";
        final String PORCENTAJE_DESCUENTO_RECARGO = "0";
        final Double VALOR_DESCUENTO_RECARGO = 0.0;
        final Double SUBTOTAL = 10.0;
        final Double TOTAL = 0.0;
        final int TAMANIO_LISTA_SERVICIOS = 1;

        //act
        Pago pago = new PagoTestDataBuilder().conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();

        //assert
        assertEquals(ID, pago.getId());
        assertEquals(FECHA_PAGO, pago.getFechaPago());
        assertEquals(IDENTIFICACION_CLIENTE, pago.getIdentificacionCliente());
        assertEquals(SUBTOTAL, pago.getSubTotal());
        assertEquals(PORCENTAJE_DESCUENTO_RECARGO, pago.getPorcentajeDescuentoRecargo());
        assertEquals(VALOR_DESCUENTO_RECARGO, pago.getValorDescuentoRecargo());
        assertEquals(TOTAL, pago.getValorDescuentoRecargo());
        assertEquals(TAMANIO_LISTA_SERVICIOS, pago.getPagoServicios().size());
    }

    @Test
    @DisplayName("Deberia fallar al crear un pago sin fecha pago")
    void deberiaFallarSinFechaPago() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conFechaPago(null).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de pago");
    }

    @Test
    @DisplayName("Deberia fallar al crear un pago sin identificacion cliente")
    void deberiaFallarSinIdentificacionCliente() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conIdentificacionCliente(null).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la identificación del cliente");
    }

    @Test
    @DisplayName("Deberia fallar al crear un pago sin el subtotal")
    void deberiaFallarSinSubtotal() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conSubTotal(null).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el subtotal");
    }

    @Test
    @DisplayName("Deberia fallar al crear un pago sin el valor total")
    void deberiaFallarSintotal() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conTotal(null).conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el valor total");
    }

    @Test
    @DisplayName("Deberia fallar al crear un pago sin el detalle de el/los servicios")
    void deberiaFallarSinServicios() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conServicioElectricoTestDataBuilders(null);

        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el detalle de los servicios a pagar");
    }
}
