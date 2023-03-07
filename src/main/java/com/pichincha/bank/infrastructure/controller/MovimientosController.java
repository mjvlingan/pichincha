package com.pichincha.bank.infrastructure.controller;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Movimiento;
import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.response.MovimientoResponse;
import com.pichincha.bank.domain.service.MovimientoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movimientos")
public class MovimientosController {

    private final MovimientoService movimientoService;

    @PostMapping(value = "/registrar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody RegisterOperationRequest request) throws RestException {
        movimientoService.registerMovimiento(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/reporte", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MovimientoResponse>> listarMovimientos(@RequestHeader("idCliente") int idCliente,
                                                      @RequestParam(value = "fechaInicio") Date fechaInicio,
                                                      @RequestParam(value = "fechaFin") Date fechaFin) throws RestException {
        return new ResponseEntity<>(movimientoService.listMovimientos(idCliente, fechaInicio, fechaFin), HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> eliminarMovimiento(@RequestHeader int idMovimiento) throws RestException {
        movimientoService.deleteMovimiento(idMovimiento);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
