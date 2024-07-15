package com.alura.forohub.forohub.controller;

import com.alura.forohub.forohub.domain.topico.DTOCrearTopico;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopico;
import com.alura.forohub.forohub.service.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DTOCrearTopico> crearTopicoJacksonTester;

    @Autowired
    private JacksonTester<DTORespuestaTopico> respuestaTopicoJacksonTester;

    @MockBean
    private TopicoService topicoService;

    @Test
    @DisplayName("Deberia retornar estado HTTP 400, cunado los datos ingresados sean invalidos")
    @WithMockUser
    void registrarEscenario1() throws Exception {

        var response= mockMvc.perform(post("/topicos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deberia retornar estado HTTP 200, cunado los datos ingresados sean validos")
    @WithMockUser
    void registrarEscenario2() throws Exception {

        var response= mockMvc.perform(post("/topicos")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(crearTopicoJacksonTester.write(new DTOCrearTopico(null,"asdasd","dnasdknaklsdka",1l,2l)).getJson())
                ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonesperado=respuestaTopicoJacksonTester.write(new DTORespuestaTopico(null,"asdasd","dnasdknaklsdka",1l,2l)).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonesperado);
    }


}