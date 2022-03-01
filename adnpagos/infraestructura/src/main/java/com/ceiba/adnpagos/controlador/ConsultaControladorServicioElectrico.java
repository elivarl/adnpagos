package com.ceiba.adnpagos.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.usuario.consulta.ManejadorListarServicioElectrico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/servicios")
@Api (tags = "Controlador consulta servicios")
public class ConsultaControladorServicioElectrico {
	private final ManejadorListarServicioElectrico listarServicioElectrico;
	
	public ConsultaControladorServicioElectrico(ManejadorListarServicioElectrico listarServicioElectrico) {
		this.listarServicioElectrico=listarServicioElectrico;
	
	}
	
	@GetMapping
	@ApiOperation("Listar usuarios")
	public List<DtoServicioElectrico> listar(){
		return listarServicioElectrico.ejecutar();
	}

}
