package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adnpagos.puerto.dao.DaoServicioElectrico;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioPago;
import com.ceiba.adnpagos.puerto.repositorio.RepositorioServicioElectrico;
import com.ceiba.usuario.servicio.ServicioCrearPago;

@Configuration
public class BeanServicioPago {
	
	@Bean
	public ServicioCrearPago crearPago(RepositorioPago repositorioPago) {
		return new ServicioCrearPago(repositorioPago);
	}

}
