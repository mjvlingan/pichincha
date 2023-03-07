package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Cuenta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterCuentaRequest {
    private Cuenta cuenta;
}
