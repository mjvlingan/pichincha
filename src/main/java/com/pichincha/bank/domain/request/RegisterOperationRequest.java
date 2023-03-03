package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.request.type.TipoMovimientoEnum;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterOperationRequest {
    private Date fecha;
    private int idCuenta;
    private TipoMovimientoEnum tipoMovimiento;
    private int valor;
}
