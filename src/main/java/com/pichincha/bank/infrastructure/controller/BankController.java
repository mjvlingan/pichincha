package com.pichincha.bank.infrastructure.controller;

import com.pichincha.bank.domain.request.RegisterAccountRequest;
import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankController {

    private final UsuarioService usuarioService;
    private final UsuarioService usuarioService;

    @PostMapping(value = "/usuario", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrarUsuario(@RequestBody RegisterUserRequest request) {
        usuarioService.saveUser(request);
    }

    @PostMapping(value = "/cuentas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrarCuenta(@RequestBody RegisterAccountRequest request) {

    }

}
