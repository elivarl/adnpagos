package com.ceiba.pago.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.adnpagos.controlador.ConsultaControladorPago;
import com.ceiba.adnpagos.controlador.ConsultaControladorServicioElectrico;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorPago.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorPagoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar los pagos")
    void deberiaListarPagos() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/pagos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].identificacionCliente", is("1717213183")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }
    @Test
    @DisplayName("Deberia listar el detalle de los pagos por id pago")
    void deberiaListarPagoDetallePorIdPago() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/pagos/pagodetalles/{idpago}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].descripcion", is("Febrero")));

    }
}
