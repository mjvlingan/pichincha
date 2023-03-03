package com.pichincha.bank.domain.service;

import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.response.MovimientoResponse;

import java.util.List;

public interface MovimientoService {

    void registerOperation(RegisterOperationRequest request);

    List<MovimientoResponse> listOperations(int idCliente, String fechaInicio, String fechaFinal);
}
