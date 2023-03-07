package com.pichincha.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.domain.request.RegisterCuentaRequest;
import com.pichincha.bank.domain.request.UpdateCuentaRequest;
import com.pichincha.bank.domain.request.type.TipoCuentaEnum;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import com.pichincha.bank.infrastructure.mapper.CuentaMapper;
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
@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CuentaController cuentaController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private CuentaMapper mapper;

    @Test
    public void registerCuentaSuccess() throws Exception {
        Cuenta cuenta = buildCuenta(1);
        RegisterCuentaRequest request = RegisterCuentaRequest.builder().cuenta(cuenta).build();
        given(cuentaController.registrarCuenta(1, request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/cuenta/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());
    }

    @Test
    public void registerCuentaFail() throws Exception {
        Cuenta cuenta = buildCuenta(1);
        RegisterCuentaRequest request = RegisterCuentaRequest.builder().cuenta(cuenta).build();
        given(cuentaController.registrarCuenta(1, request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cuenta/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void getCuentaSuccess() throws Exception {
        Cuenta cuenta = buildCuenta(1);
        given(cuentaController.obtenerCuenta(1)).willReturn(new ResponseEntity<>(cuenta, HttpStatus.OK));
        mvc.perform(get("/api/v1/cuenta/obtener").
                        header("idCuenta", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void getCuentaFail() throws Exception {
        given(cuentaController.obtenerCuenta(1)).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        mvc.perform(get("/api/v1/cuenta/obtener").
                        header("idCuenta", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound());
    }

    @Test
    public void listCuentaSuccess() throws Exception {
        List<CuentaEntity> listaCuenta = new ArrayList<>();
        listaCuenta.add(mapper.toCuentaEntity(1, buildCuenta(1), true));
        listaCuenta.add(mapper.toCuentaEntity(2, buildCuenta(2), true));
        given(cuentaController.listarCuenta(1)).willReturn(new ResponseEntity<>(listaCuenta, HttpStatus.OK));
        mvc.perform(get("/api/v1/cuenta/listar").
                        header("idCuenta", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isFound());
    }

    @Test
    public void updateCuentaSuccess() throws Exception {
        Cuenta cuenta = buildCuenta(1);
        UpdateCuentaRequest request = UpdateCuentaRequest.builder().cuenta(cuenta).build();
        given(cuentaController.actualizarCuenta(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/cuenta/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void updateCuentaFail() throws Exception {
        Cuenta cuenta = buildCuenta(1);
        UpdateCuentaRequest request = UpdateCuentaRequest.builder().cuenta(cuenta).build();
        given(cuentaController.actualizarCuenta(request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cuenta/actualizar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteCuentaSuccess() throws Exception {
        given(cuentaController.eliminarCuenta(1)).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/api/v1/cuenta/eliminar").
                        header("idCuenta", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void deleteCuentaFail() throws Exception {
        given(cuentaController.eliminarCuenta(1)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/cuenta/eliminar").
                        header("idCuenta", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    private Cuenta buildCuenta(int idCuenta) {
        return Cuenta.builder().
                idCuenta(idCuenta).
                numeroCuenta("123456789").
                tipoCuenta(TipoCuentaEnum.AHORRO).
                saldo(2000).
                estadoCuenta(true).
                build();
    }
}
