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
		System.out.println("Servicio con id:  "+id +" añadido");
		pagosDetalle.add(new ComandoPagoDetalle(id));		
	}
	
	@DeleteMapping("/{id}/detalle")
	@ApiOperation("Eliminar un servicio de un pago")
	public void deletePagoDetalle(@PathVariable Long id) {
		System.out.println("Servicio con id:  "+id +" eliminado");		
		pagosDetalle.remove(new ComandoPagoDetalle(id));		
	}
	
	
			
	@PostMapping
	@ApiOperation("Crear pago")
	public ComandoRespuesta<Long> crear (@RequestBody ComandoPago comandoPago){
				
		comandoPago.setPagosDetalle(this.pagosDetalle);
		this.pagosDetalle= new ArrayList<ComandoPagoDetalle>();
		return crearPago.ejecutar(comandoPago);
	}
	
	@GetMapping("/test")
	public void test() {
		/*System.out.println("Día de la semana: "+LocalDate.now().getDayOfWeek());
		System.out.println(LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString()));
		
		System.out.println("Test validaFecha: "+validaFechaLaboral());*/
		
		LocalDateTime ldt1 = LocalDateTime.now();
		LocalDateTime ldt2 = LocalDateTime.now();
		
				
		Duration d= Duration.between(ldt1.plusDays(5), ldt2);
		long dias= d.toDays();
		System.out.println("Duaración: "+dias);
	}
	
	
	private LocalDateTime validaFechaLaboral() {
		if (LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())||LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SUNDAY.toString())) {
			if(LocalDateTime.now().getDayOfWeek().name().equals(NoLaboral.SATURDAY.toString())) {
				return LocalDateTime.now().plusDays(2);
			}else {
				return LocalDateTime.now().plusDays(1);
			}			
		}
		return LocalDateTime.now();
	}

}
