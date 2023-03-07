package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Cliente;
import lombok.Data;

@Data
public class UpdateClienteRequest {
    private Cliente cliente;
}
