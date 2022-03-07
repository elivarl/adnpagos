package com.ceiba.adnpagos.controlador;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.adnpagos.comando.manejador.ManejadorAplicarReglasPago;
import com.ceiba.adnpagos.modelo.dto.DtoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.comando.manejador.ManejadorCrearPago;
import com.ceiba.adnpagos.modelo.entidad.NoLaboral;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pagos")
@Api(tags = "Controlador comando pagos")
public class ComandoControladorPago {
	
	private final ManejadorCrearPago crearPago;
	private final ManejadorAplicarReglasPago aplicarReglasPago;
	@Autowired
	public ComandoControladorPago(ManejadorCrearPago crearPago, ManejadorAplicarReglasPago aplicarReglasPago) {
		this.crearPago=crearPago;
		this.aplicarReglasPago=aplicarReglasPago;
	}
	
	@PostMapping("/{id}/detalle")
	@ApiOperation("Añadir un servicio a un pago")
	public DtoPago aplicarReglas(@RequestBody ComandoPago comandoPago) {
		return aplicarReglasPago.aplicarReglasPago(comandoPago);
	}

	@PostMapping
	@ApiOperation("Crear pago")
	public ComandoRespuesta<Long> crear (@RequestBody ComandoPago comandoPago){
		return crearPago.ejecutar(comandoPago);
	}


}
