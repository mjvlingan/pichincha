package com.pichincha.bank.domain.service;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.domain.request.RegisterCuentaRequest;
import com.pichincha.bank.domain.request.UpdateCuentaRequest;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;

import java.util.List;

public interface CuentaService {
    void registerAccount(int idCliente, RegisterCuentaRequest request) throws RestException;

    Cuenta findCuentaById(int idCuenta) throws RestException;

    List<CuentaEntity> findAllCuenta(int idCliente) throws RestException;

    void updateCuenta(UpdateCuentaRequest request) throws RestException;

    void deleteCuenta(int idCuenta) throws RestException;
}
