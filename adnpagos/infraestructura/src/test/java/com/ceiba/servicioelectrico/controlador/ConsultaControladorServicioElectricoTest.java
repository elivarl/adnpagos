package com.ceiba.servicioelectrico.controlador;

import com.ceiba.ApplicationMock;
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
@WebMvcTest(ConsultaControladorServicioElectrico.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class ConsultaControladorServicioElectricoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar los servicios electricos")
    void deberiaListarServiciosElectricos() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/servicios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].identificacionCliente", is("1717213183")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    @DisplayName("Deberia listar un servicio electrico por id")
    void deberiaListarUnServicioElectrico() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/servicios/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("identificacionCliente", is("1717213183")))
                .andExpect(jsonPath("id", is(1)));

    }
}
