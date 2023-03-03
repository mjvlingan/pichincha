package com.pichincha.bank.domain.service;

import com.pichincha.bank.domain.request.RegisterAccountRequest;

public interface CuentaService {
    void registerAccount(RegisterAccountRequest request);
}
