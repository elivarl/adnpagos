package com.ceiba.usuario.puerto.dao;

import java.util.List;

import com.ceiba.adnpagos.modelo.dto.DtoUsuario;

public interface DaoUsuario {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuario> listar();
}
