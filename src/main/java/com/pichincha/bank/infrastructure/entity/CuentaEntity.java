package com.pichincha.bank.infrastructure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Cuenta")
public class CuentaEntity {
    @Id
    private Integer idCuenta;
    private Integer idCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private boolean estadoCuenta;
}
