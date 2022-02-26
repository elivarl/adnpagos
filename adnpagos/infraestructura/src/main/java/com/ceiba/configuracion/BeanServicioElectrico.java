package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.usuario.servicio.ServicioCrearServicioElectrico;

@Configuration
public class BeanServicioElectrico {
	
	@Bean
	public ServicioCrearServicioElectrico servicioCrearServicioElectrico(RepositorioServicioElectrico repositorioServicioElectrico) {
		return new ServicioCrearServicioElectrico(repositorioServicioElectrico);
	}
}
