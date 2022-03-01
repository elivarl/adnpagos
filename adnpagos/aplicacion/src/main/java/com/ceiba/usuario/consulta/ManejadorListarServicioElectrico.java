package com.ceiba.usuario.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.modelo.dto.*;

@Component
public class ManejadorListarServicioElectrico {
	private final DaoServicioElectrico daoServicioElectrico;
	
	public ManejadorListarServicioElectrico(DaoServicioElectrico daoServicioElectrico) {
		this.daoServicioElectrico=daoServicioElectrico;	
	}
	
	public List<DtoServicioElectrico> ejecutar(){
		return this.daoServicioElectrico.listar();
	}

}
