package com.pichincha.bank.domain.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MovimientoResponse {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private boolean estadoCuenta;
    private String valor;
    private int saldoFinal;
}
