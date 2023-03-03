package com.pichincha.bank.domain.request;

import com.pichincha.bank.domain.request.type.GeneroEnum;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String nombre;
    private String direccion;
    private String telefono;
    private String password;
    private String edad;
    private String identificacion;
    private GeneroEnum genero;
}
