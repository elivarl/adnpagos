package com.ceiba.adnpagos.adaptador.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;

@Component
public class DaoServicioElectricoMySQL implements DaoServicioElectrico{

	@Override
	public List<DtoServicioElectrico> listat() {
		return null;
	}

}
