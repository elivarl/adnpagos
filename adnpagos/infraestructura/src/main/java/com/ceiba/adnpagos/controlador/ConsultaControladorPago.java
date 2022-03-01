package com.ceiba.adnpagos.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.usuario.consulta.ManejadorListarPagos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pagos")
@Api (tags = "Controlador consulta pagos")
public class ConsultaControladorPago {
	
	private final ManejadorListarPagos listarPagos;
	public ConsultaControladorPago(ManejadorListarPagos listarPagos) {
		this.listarPagos=listarPagos;
	}
	
	
	@GetMapping
	@ApiOperation("Listar pagos")
	public List<DtoPago> listar(){
		return this.listarPagos.ejecutar();
	}

}
