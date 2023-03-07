package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.model.Persona;
import lombok.Data;

@Data
public class RegisterPersonaRequest {
    private Persona persona;
}
