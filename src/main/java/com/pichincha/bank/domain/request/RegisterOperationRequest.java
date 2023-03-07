package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.request.type.TipoMovimientoEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RegisterOperationRequest {
    private Date fecha;
    private int idCuenta;
    private TipoMovimientoEnum tipoMovimiento;
    private int valor;
}
