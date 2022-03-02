package com.ceiba.adnpagos.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.comando.manejador.ManejadorCrearPago;

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
	public void addPagoDetalle(@PathVariable Long id) {
		System.out.println("Servicio con id:  "+id +" añadido");
		pagosDetalle.add(new ComandoPagoDetalle(id));		
	}
	
	@DeleteMapping("/{id}/detalle")
	public void deletePagoDetalle(@PathVariable Long id) {
		System.out.println("Servicio con id:  "+id +" eliminado");		
		pagosDetalle.remove(new ComandoPagoDetalle(id));		
	}
	
	
			
	@PostMapping
	@ApiOperation("Crear pago")
	public ComandoRespuesta<Long> cear (@RequestBody ComandoPago comandoPago){
				
		comandoPago.setPagosDetalle(this.pagosDetalle);
		
		return crearPago.ejecutar(comandoPago);
	}

}
