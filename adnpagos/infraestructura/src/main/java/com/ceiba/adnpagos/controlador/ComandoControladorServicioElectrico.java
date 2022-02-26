package com.ceiba.adnpagos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.comando.manejador.ManejadorCrearServicioElectrico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/servicio")
@Api(tags = {"Controlador comando servicio eléctrico"})
public class ComandoControladorServicioElectrico {
	
	
	private final ManejadorCrearServicioElectrico manejadorCrearServicioElectrico;
	
	@Autowired
	public ComandoControladorServicioElectrico(ManejadorCrearServicioElectrico manejadorCrearServicioElectrico) {
		this.manejadorCrearServicioElectrico=manejadorCrearServicioElectrico;
	}
	
	@PostMapping
	@ApiOperation("Crear servicio electrico")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoServicioElectrico comandoServicioElectrico){
		System.out.println("hello: "+comandoServicioElectrico.getIdentificacionCliente());
		return manejadorCrearServicioElectrico.ejecutar(comandoServicioElectrico);
	}
	
}
