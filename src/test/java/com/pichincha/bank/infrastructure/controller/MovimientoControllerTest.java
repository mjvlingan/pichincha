package com.pichincha.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.request.type.TipoMovimientoEnum;
import com.pichincha.bank.infrastructure.mapper.MovimientoMapper;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovimientosController.class)
public class MovimientoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovimientosController movimientoController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private MovimientoMapper mapper;

    @Test
    public void registerMovimientoSuccess() throws Exception {
        RegisterOperationRequest request = buildMovimiento(1);
        given(movimientoController.registrarMovimiento(request)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mvc.perform(get("/api/v1/movimientos/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());
    }

    @Test
    public void registerMovimientoFail() throws Exception {
        RegisterOperationRequest request = buildMovimiento(1);
        given(movimientoController.registrarMovimiento( request)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/movimientos/registrar").
                        content(objectMapper.writeValueAsString(request)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    @Test
    public void listMovimientosSuccess() throws Exception {
        given(movimientoController.listarMovimientos(2, parseDate("2022-01-07"), parseDate("2022-03-07"))).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/api/v1/movimientos/obtener").
                        header("idCliente", 2).
                        param("fechaInicio", "2022-01-07").
                        param("fechaFin", "2022-03-07").
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void listMovimientosFail() throws Exception {
        given(movimientoController.listarMovimientos(2, parseDate("2022-01-07"), parseDate("2022-03-07"))).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        mvc.perform(get("/api/v1/movimientos/obtener").
                        header("idCliente", 2).
                        param("fechaInicio", "2022-01-07").
                        param("fechaFin", "2022-03-07").
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound());
    }

    @Test
    public void deleteMovimientoSuccess() throws Exception {
        given(movimientoController.eliminarMovimiento(1)).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/api/v1/movimientos/eliminar").
                        header("idMovimiento", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void deleteMovimientoFail() throws Exception {
        given(movimientoController.eliminarMovimiento(1)).willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        mvc.perform(get("/api/v1/movimientos/eliminar").
                        header("idMovimiento", 1).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }

    private RegisterOperationRequest buildMovimiento(int idCuenta) {
        return RegisterOperationRequest.builder().
                fecha(new Date()).
                tipoMovimiento(TipoMovimientoEnum.DEPOSITO).
                valor(100).
                idCuenta(idCuenta).
                build();
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
