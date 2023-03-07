package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Persona;
import lombok.Data;

@Data
public class UpdatePersonaRequest {
    private Persona persona;
}
