package com.pichincha.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.domain.request.RegisterPersonaRequest;
import com.pichincha.bank.domain.request.UpdatePersonaRequest;
import com.pichincha.bank.domain.request.type.GeneroEnum;
import com.pichincha.bank.infrastructure.controller.PersonaController;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import com.pichincha.bank.infrastructure.mapper.PersonaMapper;
import com.pichincha.bank.infrastructure.mapper.PersonaMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonaController.class)
public class PersonaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaController personaController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private PersonaMapper mapper;

    @Test
    public void registerPersonaSuccess() throws Exception {
        Persona persona = buildPersona(1);
        RegisterPersonaRequest request = RegisterPersonaRequest.builder().persona(persona).build();
        given(personaController.registrarPersona(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/persona/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());
    }

    @Test
    public void registerPersonaFail() throws Exception {
        Persona persona = buildPersona(1);
        RegisterPersonaRequest request = RegisterPersonaRequest.builder().persona(persona).build();
        given(personaController.registrarPersona(request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/persona/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void getPersonaSuccess() throws Exception {
        Persona persona = buildPersona(1);
        given(personaController.obtenerPersona(1)).willReturn(new ResponseEntity<>(persona, HttpStatus.OK));
        mvc.perform(get("/api/v1/persona/obtener").
                header("idPersona", 1).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void getPersonaFail() throws Exception {
        given(personaController.obtenerPersona(1)).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        mvc.perform(get("/api/v1/persona/obtener").
                        header("idPersona", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound());
    }

    @Test
    public void listPersonaSuccess() throws Exception {
        List<PersonaEntity> listaPersona = new ArrayList<>();
        listaPersona.add(mapper.toPersonaEntity(buildPersona(1)));
        listaPersona.add(mapper.toPersonaEntity(buildPersona(2)));
        given(personaController.listarPersona()).willReturn(new ResponseEntity<>(listaPersona, HttpStatus.OK));
        mvc.perform(get("/api/v1/persona/listar").
                        header("idPersona", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void updatePersonaSuccess() throws Exception {
        Persona persona = buildPersona(1);
        UpdatePersonaRequest request = UpdatePersonaRequest.builder().persona(persona).build();
        given(personaController.actualizarPersona(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/persona/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void updatePersonaFail() throws Exception {
        Persona persona = buildPersona(1);
        UpdatePersonaRequest request = UpdatePersonaRequest.builder().persona(persona).build();
        given(personaController.actualizarPersona(request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/persona/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void deletePersonaSuccess() throws Exception {
        given(personaController.eliminarPersona(1)).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/api/v1/persona/eliminar").
                        header("idPersona", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void deletePersonaFail() throws Exception {
        given(personaController.eliminarPersona(1)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/persona/eliminar").
                        header("idPersona", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    private Persona buildPersona(int idPersona) {
        return Persona.builder().
                idPersona(idPersona).
                nombre("Carlos").
                genero(GeneroEnum.MASCULINO).
                edad("32").
                identificacion("44556677").
                direccion("Av. Test 123").
                telefono("999999999").
                build();
    }
}
