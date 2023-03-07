package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Cuenta;
import lombok.Data;

@Data
public class UpdateCuentaRequest {
    private Cuenta cuenta;
}
