package com.ceiba.adnpagos.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoUsuario;
import com.ceiba.adnpagos.modelo.entidad.Usuario;

@Component
public class FabricaUsuario {

    public Usuario crear(ComandoUsuario comandoUsuario) {
        return new Usuario(
                comandoUsuario.getId(),
                comandoUsuario.getNombre(),
                comandoUsuario.getClave(),
                comandoUsuario.getFecha()
        );
    }

}
