package com.pichincha.bank.infrastructure.controller;

import com.pichincha.bank.domain.request.RegisterAccountRequest;
import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.domain.response.MovimientoResponse;
import com.pichincha.bank.domain.service.CuentaService;
import com.pichincha.bank.domain.service.MovimientoService;
import com.pichincha.bank.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankController {

    private final UsuarioService usuarioService;
    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;

    @PostMapping(value = "/usuario", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrarUsuario(@RequestBody RegisterUserRequest request) {
        usuarioService.saveUser(request);
    }

    @PostMapping(value = "/cuentas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrarCuenta(@RequestBody RegisterAccountRequest request) {
        cuentaService.registerAccount(request);
    }

    @PostMapping(value = "/movimientos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrarMovimiento(@RequestBody RegisterOperationRequest request) {
        movimientoService.registerOperation(request);
    }

    @GetMapping(value = "/movimientos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MovimientoResponse> listarMovimientos(@RequestHeader("idCliente") int idCliente,
                                                      @RequestParam(value = "fechaInicio", required = true) String fechaInicio,
                                                      @RequestParam(value = "fechaFin", required = true) String fechaFin){
        return movimientoService.listOperations(idCliente, fechaInicio, fechaFin);
    }
}
