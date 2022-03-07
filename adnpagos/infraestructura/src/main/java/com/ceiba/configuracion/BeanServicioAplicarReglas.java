package com.ceiba.configuracion;

import com.ceiba.adnpagos.modelo.entidad.Pago;
import com.ceiba.usuario.servicio.ServicioAplicarReglaPago;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioAplicarReglas {
    @Bean
    public ServicioAplicarReglaPago servicioAplicarReglaPago(){
        return new ServicioAplicarReglaPago();
    }
}
