package com.ceiba.pagodetalle.servicio;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.adnpagos.modelo.entidad.PagoDetalle;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.testdatabuilder.PagoTestDataBuilder;
import com.ceiba.pago.testdatabuilder.ServicioElectricoTestDataBuilder;
import com.ceiba.pagodetalle.testdatabuilder.PagoDetalleTestDataBuilder;
import com.ceiba.usuario.servicio.ServicioCrearPago;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearPagoDetalleTest {

    @Test
    @DisplayName("Deberia Crear el pago detalle de manera correcta")
    void deberiaCrearElPagoDetalleDeManeraCorrecta() {
        // arrange
        final long ID=1L;

        PagoDetalle pagoDetalle = new PagoDetalleTestDataBuilder().build();


        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioPago.crearPagoDetalle(pagoDetalle)).thenReturn(1L);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago);

        // act
        Long idPagoDetalle = servicioCrearPago.crearPagoDetalle(pagoDetalle);
        // assert
        assertEquals(ID,idPagoDetalle);
        Mockito.verify(repositorioPago, Mockito.times(1)).crearPagoDetalle(pagoDetalle);

    }

}
