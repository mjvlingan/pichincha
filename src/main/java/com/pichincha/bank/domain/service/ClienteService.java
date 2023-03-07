package com.pichincha.bank.domain.service;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.domain.request.RegisterClienteRequest;
import com.pichincha.bank.domain.request.UpdateClienteRequest;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;

import java.util.List;

public interface ClienteService {

    void saveCliente(RegisterClienteRequest request) throws RestException;

    Cliente findCliente(int idCliente) throws RestException;

    List<ClienteEntity> getAllCliente() throws RestException;

    void updateCliente(UpdateClienteRequest request) throws RestException;

    void deleteCliente(int idCliente) throws RestException;
    
}
