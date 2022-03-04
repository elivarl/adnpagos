package com.ceiba.adnpagos.controlador;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
	
	private List<ComandoPagoDetalle>  pagosDetalle;	
	
	private final ManejadorCrearPago crearPago;
	@Autowired
	public ComandoControladorPago(ManejadorCrearPago crearPago) {
		this.crearPago=crearPago;
		this.pagosDetalle= new ArrayList<ComandoPagoDetalle>();
	}
	
	@PostMapping("/{id}/detalle")
	@ApiOperation("Añadir un servicio a un pago")
	public void addPagoDetalle(@PathVariable Long id) {
		pagosDetalle.add(new ComandoPagoDetalle(id));		
	}
	
	@DeleteMapping("/{id}/detalle")
	@ApiOperation("Eliminar un servicio de un pago")
	public void deletePagoDetalle(@PathVariable Long id) {
		pagosDetalle.remove(new ComandoPagoDetalle(id));		
	}
	
	
			
	@PostMapping
	@ApiOperation("Crear pago")
	public ComandoRespuesta<Long> crear (@RequestBody ComandoPago comandoPago){
		//comandoPago.setPagosDetalle(this.pagosDetalle);
		//this.pagosDetalle= new ArrayList<ComandoPagoDetalle>();
		return crearPago.ejecutar(comandoPago);
	}


}
