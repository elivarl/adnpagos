package com.ceiba.servicioelectrico.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.servicioelectrico.testdatabuilder.ServicioElectricoTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioElectricoTest {
    @Test
    @DisplayName("Deberia crear correctamente un servicio electrico")
    void deberiaCrearCorrectamenteElServicioElectrico() {
        // arrange
        long ID=1L;
        final String NUMERO_SERVICIO="12345";
        final String IDENTIFICACION_CLIENTE="1717213183";
        final String NOMBRE_CLIENTE="Elivar Largo";
        final String MES="Enero";
        final LocalDateTime FECHA_MAXIMO_PAGO= LocalDateTime.parse("2022-03-28T15:00:00.104");
        final Double VALOR=10.0;
        LocalDateTime FECHA_CREACION = LocalDateTime.parse("2022-03-05T15:00:00.104");

        //act
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().build();

        //assert
        assertEquals(ID, servicioElectrico.getId());
        assertEquals(NUMERO_SERVICIO, servicioElectrico.getNumeroServicio());
        assertEquals(IDENTIFICACION_CLIENTE, servicioElectrico.getIdentificacionCliente());
        assertEquals(NOMBRE_CLIENTE, servicioElectrico.getNombreCliente());
        assertEquals(MES, servicioElectrico.getMes());
        assertEquals(FECHA_MAXIMO_PAGO, servicioElectrico.getFechaMaximaPago());
        assertEquals(VALOR, servicioElectrico.getValor());
        assertEquals(FECHA_CREACION, servicioElectrico.getFechaCreacion());
    }

    @Test
    void deberiaFallarSinNumeroDeServicio() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conNumeroServicio(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el número de servicio");
    }
    @Test
    void deberiaFallarSinIdentificacionDelCliente() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conIdentificacionCliente(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la identificación del cliente");
    }

    @Test
    void deberiaFallarSinNombreDelCliente() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conNombreCliente(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del cliente");
    }

    @Test
    void deberiaFallarSinMes() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conMes(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el mes correspondiente al servicio");
    }
    @Test
    void deberiaFallarSinFechaMaximaPago() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conFechaMaximaPago(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha máxima de pago");
    }

    @Test
    void deberiaFallarSinValor() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conValor(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el valor del servicio");
    }
    @Test
    void deberiaFallarSinFechaCreacion() {
        //Arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder().conFechaCreacion(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    servicioElectricoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de creación");
    }
}
