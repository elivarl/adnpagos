package com.ceiba.usuario.consulta;

import java.util.List;

import com.ceiba.adnpagos.puerto.dao.DaoPago;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.modelo.dto.DtoPago;

@Component
public class ManejadorListarPagos {
	
	private final DaoPago daoPago;
	
	public ManejadorListarPagos(DaoPago daoPago) {
		this.daoPago=daoPago;
	}
	
	
	public List<DtoPago> ejecutar(){
		return this.daoPago.listar();
	}

}
