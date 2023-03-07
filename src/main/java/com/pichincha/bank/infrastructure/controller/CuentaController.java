package com.pichincha.bank.infrastructure.controller;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.domain.request.RegisterCuentaRequest;
import com.pichincha.bank.domain.request.UpdateCuentaRequest;
import com.pichincha.bank.domain.service.CuentaService;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping(value = "/registrar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cuenta> registrarCuenta(@RequestHeader int idCuenta, @RequestBody RegisterCuentaRequest request) throws RestException {
        cuentaService.registerAccount(idCuenta, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/obtener", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cuenta> obtenerCuenta(@RequestHeader int idCuenta) throws RestException {
        return new ResponseEntity<>(cuentaService.findCuentaById(idCuenta), HttpStatus.FOUND);
    }

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CuentaEntity>> listarCuenta(@RequestHeader int idCliente) throws RestException {
        return new ResponseEntity<>(cuentaService.findAllCuenta(idCliente), HttpStatus.OK);
    }

    @PatchMapping(value = "/actualizar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cuenta> actualizarCuenta(@RequestBody UpdateCuentaRequest request) throws RestException {
        cuentaService.updateCuenta(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> eliminarCuenta(@RequestHeader int idCuenta) throws RestException {
        cuentaService.deleteCuenta(idCuenta);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
