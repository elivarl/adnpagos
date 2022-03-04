package com.ceiba.adnpagos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.comando.manejador.ManejadorActualizarServicioElectrico;
import com.ceiba.adnpagos.comando.manejador.ManejadorCrearServicioElectrico;
import com.ceiba.adnpagos.comando.manejador.ManejadorEliminarServicioElectrico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/servicios")
@Api(tags = {"Controlador comando servicio eléctrico"})
public class ComandoControladorServicioElectrico {
	
	
	private final ManejadorCrearServicioElectrico manejadorCrearServicioElectrico;
	private final ManejadorActualizarServicioElectrico manejadorActualizarServicioElectrico;
	private final ManejadorEliminarServicioElectrico manejadorEliminarServicioElectrico;
	
	@Autowired
	public ComandoControladorServicioElectrico(ManejadorCrearServicioElectrico manejadorCrearServicioElectrico, ManejadorActualizarServicioElectrico actualizarServicioElectrico, ManejadorEliminarServicioElectrico manejadorEliminarServicioElectrico) {
		this.manejadorCrearServicioElectrico=manejadorCrearServicioElectrico;
		this.manejadorActualizarServicioElectrico=actualizarServicioElectrico;
		this.manejadorEliminarServicioElectrico= manejadorEliminarServicioElectrico;
	}
	
	@PostMapping
	@ApiOperation("Crear servicio electrico")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoServicioElectrico comandoServicioElectrico){
		System.out.println("hello: "+comandoServicioElectrico.getIdentificacionCliente());
		return manejadorCrearServicioElectrico.ejecutar(comandoServicioElectrico);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Actualizar un servicio eléctrico")
	public void actualizar (@RequestBody ComandoServicioElectrico comandoServicioElectrico, @PathVariable Long id) {
		System.out.println("id..."+id);
		comandoServicioElectrico.setId(id);
		this.manejadorActualizarServicioElectrico.ejecutar(comandoServicioElectrico);
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Eliminar un servicio eléctrico")
	public void eliminar(@PathVariable Long id) {
		this.manejadorEliminarServicioElectrico.ejecutar(id);
	}
	
}
