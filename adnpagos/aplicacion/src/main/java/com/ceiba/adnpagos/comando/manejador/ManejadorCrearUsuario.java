package com.ceiba.adnpagos.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoUsuario;
import com.ceiba.adnpagos.comando.fabrica.FabricaUsuario;
import com.ceiba.adnpagos.modelo.entidad.Usuario;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearUsuario implements ManejadorComandoRespuesta<ComandoUsuario, ComandoRespuesta<Long>> {

    private final FabricaUsuario fabricaUsuario;
    private final ServicioCrearUsuario servicioCrearUsuario;

    public ManejadorCrearUsuario(FabricaUsuario fabricaUsuario, ServicioCrearUsuario servicioCrearUsuario) {
        this.fabricaUsuario = fabricaUsuario;
        this.servicioCrearUsuario = servicioCrearUsuario;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoUsuario comandoUsuario) {
        Usuario usuario = this.fabricaUsuario.crear(comandoUsuario);
        return new ComandoRespuesta<>(this.servicioCrearUsuario.ejecutar(usuario));
    }
}
