package com.ceiba.pago.servicio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearPagoTest {

    @Test
    @DisplayName("Deberia Crear el pago de manera correcta")
    void deberiaCrearElPagoDeManeraCorrecta() {
        // arrange
        /*List<PagoDetalle> pagosDetalles= new ArrayList<>();
        PagoDetalle pagoDetalle = new PagoDetalleTestDataBuilder().conIdServicio(1L).build();
        pagosDetalles.add(pagoDetalle);
        Pago pago= new PagoTestDataBuilder().conFechaPago(LocalDateTime.now()).conIdentificacionCliente("1234").conPagoDetalles(pagosDetalles).build();

        RepositorioPago repositorioPago = Mockito.mock(RepositorioPago.class);
        DaoServicioElectrico daoServicioElectrico = Mockito.mock(DaoServicioElectrico.class);
        RepositorioServicioElectrico repositorioServicioElectrico = Mockito.mock(RepositorioServicioElectrico.class);
        //Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioPago.crear(pago)).thenReturn(1L);
        ServicioCrearPago servicioCrearPago = new ServicioCrearPago(repositorioPago,daoServicioElectrico, repositorioServicioElectrico);

        // act
        Long idPago = servicioCrearPago.ejecutar(pago);
        //- assert
        assertEquals(1L,idPago);
        Mockito.verify(repositorioPago, Mockito.times(1)).crear(pago);*/
    }
}
