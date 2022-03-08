package com.ceiba.servicioelectrico.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.servicioelectrico.testdatabuilder.ServicioElectricoTestDataBuilder;
import com.ceiba.usuario.servicio.ServicioCrearServicioElectrico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearServicioElectricoTest {

    @Test
    @DisplayName("Deberia crear un servicio")
    void deberiaCrearUnServicio() {
        // arrange
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().build();
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);
        Mockito.when(repositorioServicioElectrico.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioServicioElectrico.crear(servicioElectrico)).thenReturn(10L);
        ServicioCrearServicioElectrico crearServicio= new ServicioCrearServicioElectrico(repositorioServicioElectrico);


        // act
        Long idUsuario = crearServicio.ejecutar(servicioElectrico);
        //- assert
        assertEquals(10L,idUsuario);
        Mockito.verify(repositorioServicioElectrico, Mockito.times(1)).crear(servicioElectrico);

    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia por id del Servicio")
    void deberiaLanzarUnaExcepcionCuandoSeValidaLaExistenciaPorIdCrearUnServicio() {
        // arrange
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().build();

        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);

        Mockito.when(repositorioServicioElectrico.existePorId(1L)).thenReturn(true);
        ServicioCrearServicioElectrico crearServicio= new ServicioCrearServicioElectrico(repositorioServicioElectrico);

        // act - assert
        BasePrueba.assertThrows(() -> crearServicio.ejecutar(servicioElectrico), ExcepcionDuplicidad.class,"El id del  del servicio ya existe");

    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia por numero del Servicio")
    void deberiaLanzarUnaExcepcionCuandoSeValidaLaExistenciaPorNumeroServicioCrearUnServicio() {
        // arrange
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().build();

        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);

        Mockito.when(repositorioServicioElectrico.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearServicioElectrico crearServicio= new ServicioCrearServicioElectrico(repositorioServicioElectrico);

        // act - assert
        BasePrueba.assertThrows(() -> crearServicio.ejecutar(servicioElectrico), ExcepcionDuplicidad.class,"El numero del  del servicio ya existe");

    }

}
