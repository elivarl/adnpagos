package com.ceiba.usuario.consulta;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import org.springframework.stereotype.Component;

@Component
public class ManejadorServicioElectrico {

    private final DaoServicioElectrico daoServicioElectrico;

    public ManejadorServicioElectrico(DaoServicioElectrico daoServicioElectrico) {
        this.daoServicioElectrico = daoServicioElectrico;
    }

    public DtoServicioElectrico ejecutar(Long id){
        return this.daoServicioElectrico.obtenerServicioElectricoPorId(id);
    }
}
