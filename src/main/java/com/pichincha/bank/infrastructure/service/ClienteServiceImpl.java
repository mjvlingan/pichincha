package com.pichincha.bank.infrastructure.service;

import com.github.damianwajser.exceptions.RestException;
import com.github.damianwajser.exceptions.impl.badrequest.NotFoundException;
import com.github.damianwajser.exceptions.impl.servererror.InternalServerErrorException;
import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.domain.request.RegisterClienteRequest;
import com.pichincha.bank.domain.request.UpdateClienteRequest;
import com.pichincha.bank.domain.service.ClienteService;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import com.pichincha.bank.infrastructure.mapper.ClienteMapper;
import com.pichincha.bank.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.pichincha.bank.domain.Constantes.RESPONSE_CODE_INTERNAL_ERROR;
import static com.pichincha.bank.domain.Constantes.RESPONSE_CODE_NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper;

    @Override
    public void saveCliente(RegisterClienteRequest request) throws RestException {
        log.info("Inicio registro cliente");
        log.debug("Request: {}", request);
        ClienteEntity clienteEntity = clienteMapper.toClienteEntity(request.getCliente().getIdPersona(), request.getCliente(), true);
        try{
            log.info("Guardando cliente");
            clienteRepository.save(clienteEntity);
        }
        catch (Exception e) {
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.save}");
        }
    }

    @Override
    public Cliente findCliente(int idCliente) throws RestException {
        log.info("Inicio busqueda cliente");
        log.debug("Request: {}", idCliente);
        Optional<ClienteEntity> result = clienteRepository.findById(idCliente);
        if(result.isPresent()){
            log.info("Cliente encontrado");
            return clienteMapper.toCliente(result.get());
        }
        else{
            log.error("No se encontro el cliente");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public List<ClienteEntity> getAllCliente() throws RestException {
        log.info("Inicio listado de clientes");
        return clienteRepository.findAll();
    }

    @Override
    public void updateCliente(UpdateClienteRequest request) throws RestException {
        log.info("Inicio actualizacion cliente");
        if(clienteRepository.existsById(request.getCliente().getIdCliente())){
            log.debug("Request: {}", request);
            ClienteEntity clienteEntity = clienteMapper.toClienteEntity(
                    request.getCliente().getIdPersona(),
                    request.getCliente(),
                    request.getCliente().isEstadoCliente());
            try{
                clienteRepository.save(clienteEntity);
            }
            catch (Exception e){
                log.error("Error en metodo: {}", e.getMessage());
                throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.update}");
            }
        }
        else{
            log.error("No se encontro el cliente");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public void deleteCliente(int idCliente) throws RestException {
        try{
            log.info("Inicio eliminacion cliente");
            clienteRepository.deleteById(idCliente);
        }
        catch (Exception e){
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.delete}");
        }
    }
}
