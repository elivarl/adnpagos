package com.ceiba.pago.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.adnpagos.comando.ComandoPago;
import com.ceiba.adnpagos.comando.ComandoPagoDetalle;
import com.ceiba.adnpagos.controlador.ComandoControladorUsuario;
import com.ceiba.pago.servicio.testdatabuilder.ComandoPagoDetalleTestDataBuilder;
import com.ceiba.pago.servicio.testdatabuilder.ComandoPagoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class ComandoControladorPagoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un pago")
    void deberiaCrearUnPago() throws Exception{
        // arrange
        List<ComandoPagoDetalle> comandoPagoDetalles = new ArrayList<>();
        ComandoPagoDetalle comandoPagoDetalle = new ComandoPagoDetalleTestDataBuilder().conIdServicio(1L).build();
        comandoPagoDetalles.add(comandoPagoDetalle);
        ComandoPago comandoPago = new ComandoPagoTestDataBuilder().conFechaPago(LocalDateTime.now()).conIdentificacionCliente("1234").conPagoDetalles(comandoPagoDetalles).build();
        ComandoPago cp= new ComandoPago();
        // act - assert
        mocMvc.perform(post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoPago)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }
}
