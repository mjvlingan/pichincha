package com.pichincha.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.domain.request.RegisterClienteRequest;
import com.pichincha.bank.domain.request.UpdateClienteRequest;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import com.pichincha.bank.infrastructure.mapper.ClienteMapper;
import com.pichincha.bank.infrastructure.mapper.ClienteMapperImpl;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteController clienteController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ClienteMapper mapper;

    @Test
    public void registerClienteSuccess() throws Exception {
        Cliente cliente = buildCliente(1);
        RegisterClienteRequest request = RegisterClienteRequest.builder().cliente(cliente).build();
        given(clienteController.registrarCliente(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/cliente/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());
    }

    @Test
    public void registerClienteFail() throws Exception {
        Cliente cliente = buildCliente(1);
        RegisterClienteRequest request = RegisterClienteRequest.builder().cliente(cliente).build();
        given(clienteController.registrarCliente(request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cliente/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void getClienteSuccess() throws Exception {
        Cliente cliente = buildCliente(1);
        given(clienteController.obtenerCliente(1)).willReturn(new ResponseEntity<>(cliente, HttpStatus.OK));
        mvc.perform(get("/api/v1/cliente/obtener").
                        header("idCliente", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void getClienteFail() throws Exception {
        given(clienteController.obtenerCliente(1)).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        mvc.perform(get("/api/v1/cliente/obtener").
                        header("idCliente", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound());
    }

    @Test
    public void listClienteSuccess() throws Exception {
        List<ClienteEntity> listaCliente = new ArrayList<>();
        listaCliente.add(mapper.toClienteEntity(1, buildCliente(1), true));
        listaCliente.add(mapper.toClienteEntity(2, buildCliente(2), true));
        given(clienteController.listarCliente()).willReturn(new ResponseEntity<>(listaCliente, HttpStatus.OK));
        mvc.perform(get("/api/v1/cliente/listar").
                        header("idCliente", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void updateClienteSuccess() throws Exception {
        Cliente cliente = buildCliente(1);
        UpdateClienteRequest request = UpdateClienteRequest.builder().cliente(cliente).build();
        given(clienteController.actualizarCliente(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/cliente/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void updateClienteFail() throws Exception {
        Cliente cliente = buildCliente(1);
        UpdateClienteRequest request = UpdateClienteRequest.builder().cliente(cliente).build();
        given(clienteController.actualizarCliente(request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cliente/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteClienteSuccess() throws Exception {
        given(clienteController.eliminarCliente(1)).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/api/v1/cliente/eliminar").
                        header("idCliente", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void deleteClienteFail() throws Exception {
        given(clienteController.eliminarCliente(1)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cliente/eliminar").
                        header("idCliente", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    private Cliente buildCliente(int idCliente) {
        return Cliente.builder().
                idCliente(idCliente).
                password("123456789").
                estadoCliente(true).
                build();
    }
}
