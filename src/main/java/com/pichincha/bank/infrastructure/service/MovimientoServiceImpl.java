package com.pichincha.bank.infrastructure.service;

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
import java.util.List;
import java.util.Optional;

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
    public void registerOperation(RegisterOperationRequest request) {
        Optional<CuentaEntity> result = cuentaRepository.findById(request.getIdCuenta());
        if(result.isPresent()) {
            CuentaEntity cuentaEntity = result.get();
            int saldoActual = cuentaEntity.getSaldo();
            if ((request.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO) && saldoActual>=request.getValor())
                    || request.getTipoMovimiento().equals(TipoMovimientoEnum.DEPOSITO)) {
                int saldoFinal = saldoActual + request.getValor();
                cuentaEntity.setSaldo(saldoFinal);
                cuentaRepository.save(cuentaEntity);
                MovimientoEntity movimientoEntity = movimientoMapper.toMovimientoEntity(request, saldoActual, saldoFinal);
                movimientoRepository.save(movimientoEntity);
            }
        }
    }

    @Override
    public List<MovimientoResponse> listOperations(int idCliente, String fechaInicio, String fechaFinal) {
        List<MovimientoResponse> movimientoResponseList = new ArrayList<>();
        Optional<ClienteEntity> resultCliente = clienteRepository.findById(idCliente);
        if(resultCliente.isPresent()){
            ClienteEntity cliente = resultCliente.get();
            PersonaEntity personaEntity = personaRepository.findById(cliente.getIdPersona()).get();
            List<CuentaEntity> cuentaEntityList = cuentaRepository.findAllByIdCliente(cliente.getIdCliente());
            cuentaEntityList.forEach(c -> {
                List<MovimientoEntity> movimientoEntityList = movimientoRepository.findAllByIdCuenta(c.getIdCuenta());
                movimientoEntityList.forEach(m -> {
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
                });
            });
        }
        return movimientoResponseList;
    }
}
