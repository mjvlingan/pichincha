package com.pichincha.bank.infrastructure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Movimientos")
public class MovimientosEntity {
    private Integer idMovimiento;
    private Date fecha;
    private Integer idCuenta;
    private String tipoMovimiento;
    private Integer idCliente;
    private int valor;
    private int saldo;
}
