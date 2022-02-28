package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.usuario.servicio.ServicioActualizarServicioElectrico;
import com.ceiba.usuario.servicio.ServicioCrearServicioElectrico;
import com.ceiba.usuario.servicio.ServicioEliminarServicioElectrico;

@Configuration
public class BeanServicioElectrico {
	
	@Bean
	public ServicioCrearServicioElectrico servicioCrearServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		return new ServicioCrearServicioElectrico(repositorioServicioElectrico);
	}
	
	 @Bean
	 public ServicioActualizarServicioElectrico servicioActualizarElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		 return new ServicioActualizarServicioElectrico(repositorioServicioElectrico);
	 }
	 
	 @Bean
	 public ServicioEliminarServicioElectrico servvicioEliminarServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		 return new ServicioEliminarServicioElectrico(repositorioServicioElectrico);
	 }
}