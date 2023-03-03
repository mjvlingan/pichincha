package com.pichincha.bank.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cuenta {
    private int idCuenta;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private boolean estadoCuenta;
    private List<Movimientos> movimientos;
}
