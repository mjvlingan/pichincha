package com.pichincha.bank.domain.service;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.domain.model.Persona;

import java.util.List;

public interface UsuarioService {

    void saveUser(RegisterUserRequest request);
}
