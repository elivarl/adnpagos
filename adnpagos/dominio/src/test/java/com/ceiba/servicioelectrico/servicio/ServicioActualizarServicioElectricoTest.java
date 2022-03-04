package com.ceiba.servicioelectrico.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.Usuario;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioUsuario;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.servicioelectrico.testdatabuilder.ServicioElectricoTestDataBuilder;
import com.ceiba.usuario.servicio.ServicioActualizarServicioElectrico;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarServicioElectricoTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del servicio")
    void deberiaValidarLaExistenciaPreviaDelServicio() {
        // arrange
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().conId(1L).build();
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);
        Mockito.when(repositorioServicioElectrico.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarServicioElectrico actualizarServicio= new ServicioActualizarServicioElectrico(repositorioServicioElectrico);

        // act - assert
        BasePrueba.assertThrows(() -> actualizarServicio.ejeuctar (servicioElectrico), ExcepcionDuplicidad.class,"El numero de servicio no existe");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        ServicioElectrico servicioElectrico = new ServicioElectricoTestDataBuilder().conId(1L).build();
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);

        Mockito.when(repositorioServicioElectrico.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarServicioElectrico actualizarServicio= new ServicioActualizarServicioElectrico(repositorioServicioElectrico);
        // act
        actualizarServicio.ejeuctar(servicioElectrico);
        //assert
        Mockito.verify(repositorioServicioElectrico,Mockito.times(1)).actualizar(servicioElectrico);
    }
}
