package com.pichincha.bank.domain.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Cliente extends Persona {
    private int idCliente;
    private String password;
    private boolean estadoCliente;
    private List<Cuenta> cuentas;
}
