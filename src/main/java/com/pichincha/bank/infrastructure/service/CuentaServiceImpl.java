package com.pichincha.bank.infrastructure.service;

import com.github.damianwajser.exceptions.RestException;
import com.github.damianwajser.exceptions.impl.badrequest.NotFoundException;
import com.github.damianwajser.exceptions.impl.servererror.InternalServerErrorException;
import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.domain.request.RegisterCuentaRequest;
import com.pichincha.bank.domain.request.UpdateCuentaRequest;
import com.pichincha.bank.domain.service.CuentaService;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import com.pichincha.bank.infrastructure.mapper.CuentaMapper;
import com.pichincha.bank.infrastructure.repository.CuentaRepository;
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
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    private final CuentaMapper cuentaMapper;

    @Override
    public void registerAccount(int idCliente, RegisterCuentaRequest request) throws RestException {
        log.info("Inicio registro cuenta");
        log.debug("Request: {}", request);
        CuentaEntity cuentaEntity = cuentaMapper.toCuentaEntity(idCliente, request.getCuenta(), true);
        try{
            log.info("Guardando cuenta");
            cuentaRepository.save(cuentaEntity);
        }
        catch (Exception e) {
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.save}");
        }
    }

    @Override
    public Cuenta findCuentaById(int idCuenta) throws RestException {
        log.info("Inicio obtener cuenta");
        log.debug("Request: {}", idCuenta);
        Optional<CuentaEntity> result = cuentaRepository.findById(idCuenta);
        if(result.isPresent()){
            return cuentaMapper.toCuenta(result.get());
        }
        else{
            log.error("Error al encontrar cuenta");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public List<CuentaEntity> findAllCuenta(int idCliente) throws RestException {
        return cuentaRepository.findAllByIdCliente(idCliente);
    }

    @Override
    public void updateCuenta(UpdateCuentaRequest request) throws RestException {
        log.info("Inicio actualizacion cuenta");
        if(cuentaRepository.existsById(request.getCuenta().getIdCuenta())){
            log.debug("Request: {}", request);
            CuentaEntity cuentaEntity = cuentaMapper.toCuentaEntityUpdate(request.getCuenta(), request.getCuenta().isEstadoCuenta());
            try{
                cuentaRepository.save(cuentaEntity);
            }
            catch (Exception e){
                log.error("Error en metodo: {}", e.getMessage());
                throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.update}");
            }
        }
        else{
            log.error("No se encontro la cuenta");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public void deleteCuenta(int idCuenta) throws RestException {
        try{
            log.info("Inicio eliminacion cuenta");
            cuentaRepository.deleteById(idCuenta);
        }
        catch (Exception e){
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.delete}");
        }
    }
}
