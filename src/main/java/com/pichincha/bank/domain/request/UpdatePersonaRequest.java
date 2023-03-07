package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Persona;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePersonaRequest {
    private Persona persona;
}
