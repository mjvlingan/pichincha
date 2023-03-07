package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Cliente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateClienteRequest {
    private Cliente cliente;
}
