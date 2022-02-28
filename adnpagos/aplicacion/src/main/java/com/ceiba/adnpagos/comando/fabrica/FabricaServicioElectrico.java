package com.ceiba.adnpagos.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;

@Component
public class FabricaServicioElectrico {
	
	public ServicioElectrico crear(ComandoServicioElectrico comandoServicioElectrico) {
		return new ServicioElectrico( comandoServicioElectrico.getId(),comandoServicioElectrico.getNumeroServicio(), comandoServicioElectrico.getIdentificacionCliente(),comandoServicioElectrico.getNombreCliente() ,comandoServicioElectrico.getMesPago(), comandoServicioElectrico.getFechaMaximaPago(),comandoServicioElectrico.getValor() ,comandoServicioElectrico.isEstado() ,comandoServicioElectrico.getFechaCreacion());
	}

}
