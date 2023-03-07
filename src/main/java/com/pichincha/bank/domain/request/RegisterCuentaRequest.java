package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Cuenta;
import lombok.Data;

@Data
public class RegisterCuentaRequest {
    private Cuenta cuenta;
}
