package com.ceiba.adnpagos.controlador;

import java.util.List;

import com.ceiba.adnpagos.modelo.dto.DtoPagoDetalle;
import com.ceiba.usuario.consulta.ManejadorListarPagoDetalle;
import org.springframework.web.bind.annotation.*;

import com.ceiba.adnpagos.modelo.dto.DtoPago;
import com.ceiba.usuario.consulta.ManejadorListarPagos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pagos")
@Api (tags = "Controlador consulta pagos")
public class ConsultaControladorPago {
	
	private final ManejadorListarPagos listarPagos;
	private final ManejadorListarPagoDetalle manejadorListarPagoDetalle;
	public ConsultaControladorPago(ManejadorListarPagos listarPagos, ManejadorListarPagoDetalle manejadorListarPagoDetalle) {
		this.listarPagos=listarPagos;
		this.manejadorListarPagoDetalle = manejadorListarPagoDetalle;
	}
	
	
	@GetMapping
	@ApiOperation("Listar pagos")
	public List<DtoPago> listar(){
		return this.listarPagos.ejecutar();
	}

	@GetMapping("/pagodetalles/{idpago}")
	@ApiOperation("Obtener los detalles de un pago")
	public List<DtoPagoDetalle> listarPorIdPago(@PathVariable Long idpago) {
		return manejadorListarPagoDetalle.ejecutar(idpago);
	}


}
