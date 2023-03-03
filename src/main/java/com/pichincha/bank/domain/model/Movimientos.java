package com.pichincha.bank.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
public class Movimientos {
    private int idMovimiento;
    private Date fecha;
    private String tipoMovimiento;
    private int valor;
    private int saldo;
}
