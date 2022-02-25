package com.ceiba.usuario.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.entidad.Usuario;
import com.ceiba.usuario.comando.ComandoUsuario;

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
