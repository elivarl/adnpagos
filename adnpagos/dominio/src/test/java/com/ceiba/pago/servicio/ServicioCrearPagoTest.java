package com.ceiba.pago.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.pago.testdatabuilder.ServicioElectricoTestDataBuilder;
import com.ceiba.pagodetalle.testdatabuilder.PagoDetalleTestDataBuilder;
import com.ceiba.usuario.servicio.ServicioActualizarServicioElectrico;
import com.ceiba.usuario.servicio.ServicioCrearPago;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearPagoTest {

    @Test
    @DisplayName("Deberia Crear el pago de manera correcta")
    void deberiaCrearElPagoDeManeraCorrecta() {
        // arrange
        final long ID = 2L;

        //objetos test data builder
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

        Pago pago = new PagoTestDataBuilder().conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();
        pago.setReglapFechaPagoLaboral();
        pago.setAplicarReglaPorcentajeDescuentoRecargo();
        pago.setPagoDetalles();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioPago.crear(pago)).thenReturn(2L);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        // act
        Long idPago = servicioCrearPago.ejecutar(pago);
        //- assert
        assertEquals(2L, idPago);
        Mockito.verify(repositorioPago, Mockito.times(1)).crear(pago);

    }

    @Test
    @DisplayName("Deberia Crear el pago detalle de manera correcta")
    void deberiaCrearElPagoDetalleDeManeraCorrecta() {
        // arrange
        final long ID = 2L;

        PagoDetalle pagoDetalle = new PagoDetalleTestDataBuilder().build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioPago.crearPagoDetalle(pagoDetalle)).thenReturn(2L);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        // act
        Long idPagoDetalle = servicioCrearPago.crearPagoDetalle(pagoDetalle);
        //- assert
        assertEquals(ID, idPagoDetalle);
        Mockito.verify(repositorioPago, Mockito.times(1)).crearPagoDetalle(pagoDetalle);

    }

    //detalle
    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarElServicioCorrectamenteEnElRepositorio() {
        // arrange
        ServicioElectricoTestDataBuilder servicioElectricoTestDataBuilder = new ServicioElectricoTestDataBuilder();
        List<ServicioElectricoTestDataBuilder> servicioElectricoTestDataBuilders = new ArrayList<>();
        servicioElectricoTestDataBuilders.add(servicioElectricoTestDataBuilder);

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        Pago pago = new PagoTestDataBuilder().conServicioElectricoTestDataBuilders(servicioElectricoTestDataBuilders).build();
        pago.setPagoDetalles();
        pago.setEstadoServicio();



        Mockito.when(repositorioPago.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);
        // act
        servicioCrearPago.actualizarServicioElectrico(pago.getPagoServicios().get(0));
        //assert
        Mockito.verify(repositorioPago, Mockito.times(1)).actualizarServivioElectrico (pago.getPagoServicios().get(0));
    }
}
