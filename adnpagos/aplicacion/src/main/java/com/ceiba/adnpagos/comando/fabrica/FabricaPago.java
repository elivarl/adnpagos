package com.ceiba.adnpagos.comando.fabrica;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.modelo.entidad.ServicioElectrico;
import org.springframework.stereotype.Component;

import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.modelo.entidad.Pago;

@Component
public class FabricaPago {
	
		
	public Pago crear (ComandoPago comandoPago) {
		return new Pago(comandoPago.getId(), comandoPago.getFechaPago(), comandoPago.getIdentificacionCliente(), comandoPago.getSubTotal(), comandoPago.getTotal(),convertirAServicioElectrico(comandoPago.getComandoServicioElectricos()));
	}

	private List<ServicioElectrico> convertirAServicioElectrico(List<ComandoServicioElectrico> comandoServicioElectricos){

		ArrayList<ServicioElectrico> servicioElectricos= new ArrayList<>();
		
		for (ComandoServicioElectrico comandoServicioElectrico : comandoServicioElectricos) {
			servicioElectricos.add(new ServicioElectrico(comandoServicioElectrico.getId(), comandoServicioElectrico.getNumeroServicio(), comandoServicioElectrico.getIdentificacionCliente(), comandoServicioElectrico.getNombreCliente(), comandoServicioElectrico.getMes(), comandoServicioElectrico.getFechaMaximaPago(), comandoServicioElectrico.getValor()));
		}
		return servicioElectricos;
	}

}
