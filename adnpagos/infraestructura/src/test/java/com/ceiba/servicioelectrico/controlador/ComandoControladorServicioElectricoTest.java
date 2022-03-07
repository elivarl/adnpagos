package com.ceiba.servicioelectrico.controlador;


import com.ceiba.ApplicationMock;
import com.ceiba.adnpagos.comando.ComandoServicioElectrico;
import com.ceiba.adnpagos.controlador.ComandoControladorServicioElectrico;
import com.ceiba.servicioelectrico.servicio.testdatabuilder.ComandoServicioElectricoTestDataBuilder;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorServicioElectrico.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorServicioElectricoTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un servicio Electrico")
    void deberiaCrearUnServicioElectrico() throws Exception{
        // arrange
        ComandoServicioElectrico servicioElectrico = new ComandoServicioElectricoTestDataBuilder().conId(4L).build();
        // act - assert
        mocMvc.perform(post("/servicios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicioElectrico)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }

    @Test
    @DisplayName("Deberia actualizar un servicio Electrico")
    void deberiaActualizarUnServicioElectrico() throws Exception{
        // arrange
        Long id = 1L;
        ComandoServicioElectrico servicioElectrico = new ComandoServicioElectricoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/servicios/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicioElectrico)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar un servicio Electrico")
    void deberiaEliminarServicioElectrico() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/servicios/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/servicios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
