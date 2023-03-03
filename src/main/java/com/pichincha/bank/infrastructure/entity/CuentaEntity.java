package com.pichincha.bank.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Cuenta")
public class CuentaEntity {
    @Id
    private Integer idCuenta;
    private Integer idCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldo;
    private boolean estadoCuenta;
}
