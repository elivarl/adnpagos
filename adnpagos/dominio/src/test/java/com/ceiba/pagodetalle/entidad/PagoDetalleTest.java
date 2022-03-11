package com.ceiba.pagodetalle.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pagodetalle.testdatabuilder.PagoDetalleTestDataBuilder;
import com.ceiba.servicioelectrico.testdatabuilder.ServicioElectricoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagoDetalleTest {
    @Test
    @DisplayName("Deberia crear correctamente un pago detalle")
    void deberiaCrearCorrectamenteUnPagoDetalle() {
        // arrange
        long ID=1L;
        final String DESCRIPCION="Pago mes Enero";
        final Double VALOR=10.0;
        final long ID_SERVICIO=1L;
        final long ID_PAGO=1L;

        //act
        PagoDetalle pagoDetalle = new PagoDetalleTestDataBuilder().build();

        //assert
        assertEquals(ID, pagoDetalle.getId());
        assertEquals(DESCRIPCION, pagoDetalle.getDescripcion());
        assertEquals(VALOR, pagoDetalle.getValor());
        assertEquals(ID_SERVICIO, pagoDetalle.getIdServicio());

    }

    @Test
    @DisplayName("Deberia fallar al crear un pago detalle sin descripcion")
    void deberiaFallarSinDescripcion() {
        //Arrange
        PagoDetalleTestDataBuilder pagoDetalleTestDataBuilder = new PagoDetalleTestDataBuilder().conDescripcion(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoDetalleTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la descripcion para el mes de pago");
    }
    @Test
    @DisplayName("Deberia fallar al crear un pago detalle sin valor")
    void deberiaFallarSinValor() {
        //Arrange
        PagoDetalleTestDataBuilder pagoDetalleTestDataBuilder = new PagoDetalleTestDataBuilder().conValor(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoDetalleTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el valor del pago para el mes de servicio");
    }
}
