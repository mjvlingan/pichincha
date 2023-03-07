package com.pichincha.bank.domain.service;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.response.MovimientoResponse;

import java.util.Date;
import java.util.List;

public interface MovimientoService {

    void registerMovimiento(RegisterOperationRequest request) throws RestException;

    List<MovimientoResponse> listMovimientos(int idCliente, Date fechaInicio, Date fechaFinal) throws RestException;

    void deleteMovimiento(int idMovimiento) throws RestException;
}
