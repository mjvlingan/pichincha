package com.pichincha.bank.infrastructure.service;

import com.github.damianwajser.exceptions.RestException;
import com.github.damianwajser.exceptions.impl.badrequest.ConflictException;
import com.github.damianwajser.exceptions.impl.badrequest.NotFoundException;
import com.github.damianwajser.exceptions.impl.servererror.InternalServerErrorException;
import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.domain.request.type.TipoMovimientoEnum;
import com.pichincha.bank.domain.response.MovimientoResponse;
import com.pichincha.bank.domain.service.MovimientoService;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import com.pichincha.bank.infrastructure.entity.MovimientoEntity;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import com.pichincha.bank.infrastructure.mapper.MovimientoMapper;
import com.pichincha.bank.infrastructure.repository.ClienteRepository;
import com.pichincha.bank.infrastructure.repository.CuentaRepository;
import com.pichincha.bank.infrastructure.repository.MovimientoRepository;
import com.pichincha.bank.infrastructure.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.pichincha.bank.domain.Constantes.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;

    private final MovimientoMapper movimientoMapper;

    @Override
    public void registerMovimiento(RegisterOperationRequest request) throws RestException {
        log.info("Inicio registro movimiento");
        log.debug("Request: {}", request);
        Optional<CuentaEntity> result = cuentaRepository.findById(request.getIdCuenta());
        if(result.isPresent()) {
            CuentaEntity cuentaEntity = result.get();
            int saldoActual = cuentaEntity.getSaldo();
            if(saldoActual == 0 && request.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO)){
                log.error("No hay suficiente saldo, saldo disponible: {}", saldoActual);
                throw new ConflictException(RESPONSE_CODE_CONFLICT, "{error.operation.balance}");
            }
            if ((request.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO) && saldoActual>=request.getValor())
                    || request.getTipoMovimiento().equals(TipoMovimientoEnum.DEPOSITO)) {
                int saldoFinal = saldoActual + request.getValor();
                cuentaEntity.setSaldo(saldoFinal);
                try{
                    log.info("Guardando cambios en cuenta: {}", cuentaEntity);
                    cuentaRepository.save(cuentaEntity);
                    MovimientoEntity movimientoEntity = movimientoMapper.toMovimientoEntity(request, saldoActual, saldoFinal);
                    log.info("Guardando movimiento: {}", movimientoEntity);
                    movimientoRepository.save(movimientoEntity);
                }
                catch (Exception e) {
                    log.error("Error en metodo: {}", e.getMessage());
                    throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.operation.register}");
                }
            }
        }
        else{
            log.error("No se encontro la cuenta");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find.account}");
        }
    }

    @Override
    public List<MovimientoResponse> listMovimientos(int idCliente, Date fechaInicio, Date fechaFinal) throws RestException  {
        log.info("Inicio de listar movimientos");
        List<MovimientoResponse> movimientoResponseList = new ArrayList<>();
        Optional<ClienteEntity> resultCliente = clienteRepository.findById(idCliente);
        if(resultCliente.isPresent()){
            ClienteEntity cliente = resultCliente.get();
            log.debug("Cliente encontrado: {}", cliente);
            PersonaEntity personaEntity = personaRepository.findById(cliente.getIdPersona()).get();
            List<CuentaEntity> cuentaEntityList = cuentaRepository.findAllByIdCliente(cliente.getIdCliente());
            log.debug("Cuentas de cliente: {}", cuentaEntityList);
            cuentaEntityList.forEach(c -> {
                List<MovimientoEntity> movimientoEntityList = movimientoRepository.findAllByIdCuenta(c.getIdCuenta());
                movimientoEntityList.forEach(m -> {
                    if(m.getFecha().after(fechaInicio) && m.getFecha().before(fechaFinal)){
                        MovimientoResponse movimiento = MovimientoResponse.builder().
                                fecha(m.getFecha()).
                                cliente(personaEntity.getNombre()).
                                numeroCuenta(c.getNumeroCuenta()).
                                tipoCuenta(c.getTipoCuenta()).
                                saldoInicial(m.getSaldoInicial()).
                                estadoCuenta(c.isEstadoCuenta()).
                                valor(m.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO.name()) ? "-"+m.getValor() : String.valueOf(m.getValor())).
                                saldoFinal(m.getSaldoFinal()).
                                build();
                        movimientoResponseList.add(movimiento);
                    }
                });
            });
        }
        else{
            log.error("No se encontro cliente");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find.client}");
        }
        log.info("Movimientos de la cuenta: {}", movimientoResponseList);
        return movimientoResponseList;
    }

    @Override
    public void deleteMovimiento(int idMovimiento) throws RestException {
        try{
            log.info("Inicio eliminacion movimiento");
            movimientoRepository.deleteById(idMovimiento);
        }
        catch (Exception e){
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.delete}");
        }
    }
}
