package com.ceiba.servicioelectrico.servicio;

import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarServicioElectrico;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarServicioElectricoTest {
    @Test
    @DisplayName("Deberia eliminar el servicio llamando al repositorio")
    void deberiaEliminarElServicioLlamandoAlRepositorio() {
        RepositorioServicioElectrico repositorioServicioElectrico =Mockito.mock(RepositorioServicioElectrico.class);
        ServicioEliminarServicioElectrico servicioEliminarServicioElectrico= new ServicioEliminarServicioElectrico(repositorioServicioElectrico);

        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        ServicioEliminarUsuario servicioEliminarUsuario = new ServicioEliminarUsuario(repositorioUsuario);

        servicioEliminarServicioElectrico.ejecutar(1l);

        Mockito.verify(repositorioServicioElectrico, Mockito.times(1)).eliminar(1l);

    }
}
