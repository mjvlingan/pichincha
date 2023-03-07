package com.pichincha.bank.domain.model;

import com.github.damianwajser.validator.annotation.enums.MatchEnum;
import com.pichincha.bank.domain.Constantes;
import com.pichincha.bank.domain.request.type.TipoCuentaEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cuenta {
    private int idCuenta;
    private String numeroCuenta;
    @MatchEnum(businessCode = Constantes.ENUM_VALIDATION,
            message = "{invalid.enum}", enumClass = TipoCuentaEnum.class)
    private TipoCuentaEnum tipoCuenta;
    private int saldo;
    private boolean estadoCuenta;
    private List<Movimiento> movimientos;
}
