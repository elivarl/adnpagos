package com.ceiba.adnpagos.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private ManejadorCrearPago crearPago;
	@Autowired
	public ComandoControladorPago(ManejadorCrearPago crearPago) {
		this.crearPago=crearPago;
	}
	
	@PostMapping
	@ApiOperation("Crear pago")
	public ComandoRespuesta<Long> cear (@RequestBody ComandoPago comandoPago){
		System.out.println(comandoPago.getValorTotal());
		
				
		ComandoPagoDetalle cpd1= new ComandoPagoDetalle(null, "test1", 1L, null);
		ComandoPagoDetalle cpd2= new ComandoPagoDetalle(null, "test2", 2L, null);
		ComandoPagoDetalle cpd3= new ComandoPagoDetalle(null, "test3", 3L, null);
		
		List<ComandoPagoDetalle> pagosDetalle = new ArrayList<>();
		pagosDetalle.add(cpd1);
		pagosDetalle.add(cpd2);
		pagosDetalle.add(cpd3);
		
		comandoPago.setPagosDetalle(pagosDetalle);
		
		
		return crearPago.ejecutar(comandoPago);
	}

}
