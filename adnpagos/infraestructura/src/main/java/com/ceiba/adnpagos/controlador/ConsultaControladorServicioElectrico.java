package com.ceiba.adnpagos.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorServicioElectrico;
import org.springframework.web.bind.annotation.*;

import com.ceiba.adnpagos.modelo.dto.DtoServicioElectrico;
import com.ceiba.usuario.consulta.ManejadorListarServiciosElectricos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/servicios")
@Api (tags = "Controlador consulta servicios")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaControladorServicioElectrico {
	private final ManejadorListarServiciosElectricos listarServicioElectrico;
	private final ManejadorServicioElectrico servicioElectrico;
	
	public ConsultaControladorServicioElectrico(ManejadorListarServiciosElectricos listarServicioElectrico, ManejadorServicioElectrico servicioElectrico) {
		this.listarServicioElectrico=listarServicioElectrico;
		this.servicioElectrico = servicioElectrico;
	}
	
	@GetMapping
	@ApiOperation("Listar usuarios")
	public List<DtoServicioElectrico> listar(){
		return listarServicioElectrico.ejecutar();
	}

	@GetMapping("/{id}")
	public DtoServicioElectrico obtenerPorId(@PathVariable Long id){
		return this.servicioElectrico.ejecutar(id);
	}


}
