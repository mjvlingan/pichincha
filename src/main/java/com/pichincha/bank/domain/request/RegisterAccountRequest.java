package com.pichincha.bank.domain.request;

import lombok.Data;

@Data
public class RegisterAccountRequest {
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private int idCliente;
}
