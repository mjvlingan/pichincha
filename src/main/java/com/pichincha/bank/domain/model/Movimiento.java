package com.pichincha.bank.domain.model;

import com.github.damianwajser.validator.annotation.enums.MatchEnum;
import com.pichincha.bank.domain.Constantes;
import com.pichincha.bank.domain.request.type.TipoMovimientoEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Movimiento {
    private int idMovimiento;
    private Date fecha;
    @MatchEnum(businessCode = Constantes.ENUM_VALIDATION,
            message = "{invalid.enum}", enumClass = TipoMovimientoEnum.class)
    private TipoMovimientoEnum tipoMovimiento;
    private int valor;
    private int saldo;
}
