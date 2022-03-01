package com.ceiba.usuario.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.adnpagos.puerto.dao.DaoPagos;

@Component
public class ManejadorListarPagos {
	
	private final DaoPagos daoPagos;
	
	public ManejadorListarPagos(DaoPagos daoPagos) {
		this.daoPagos=daoPagos;
	}
	
	
	public List<DtoPago> ejecutar(){
		return this.daoPagos.listar();
	}

}
