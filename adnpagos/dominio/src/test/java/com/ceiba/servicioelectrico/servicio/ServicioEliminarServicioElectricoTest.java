package com.ceiba.servicioelectrico.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.servicio.ServicioEliminarServicioElectrico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarServicioElectricoTest {
    @Test
    @DisplayName("Deberia eliminar el servicio llamando al repositorio")
    void deberiaEliminarElServicioLlamandoAlRepositorio() {
        // arrange
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);
        ServicioEliminarServicioElectrico servicioEliminarServicioElectrico= new ServicioEliminarServicioElectrico(repositorioServicioElectrico);
        Mockito.when(repositorioServicioElectrico.existePorId(Mockito.anyLong())).thenReturn(true);

        // act - assert
        servicioEliminarServicioElectrico.ejecutar(1l);

        Mockito.verify(repositorioServicioElectrico, Mockito.times(1)).eliminar(1l);

    }

    @Test
    @DisplayName("Deberia falla al eliminar el servicio cuando el id no existe")
    void deberiaFallaEliminarElServicioCuandoIdNoExiste() {
        // arrange
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);
        ServicioEliminarServicioElectrico servicioEliminarServicioElectrico= new ServicioEliminarServicioElectrico(repositorioServicioElectrico);
        Mockito.when(repositorioServicioElectrico.existePorId(1L)).thenReturn(false);

        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarServicioElectrico.ejecutar(1L), ExcepcionDuplicidad.class,"El id del servicio no existe");

    }
}
