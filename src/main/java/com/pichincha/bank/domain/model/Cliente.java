package com.pichincha.bank.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
public class Cliente extends Persona {
    private int idCliente;
    private String password;
    private boolean estadoCliente;
    private List<Cuenta> cuentas;
}
