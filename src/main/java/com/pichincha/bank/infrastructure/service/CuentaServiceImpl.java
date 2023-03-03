package com.pichincha.bank.infrastructure.service;

import com.pichincha.bank.domain.request.RegisterAccountRequest;
import com.pichincha.bank.domain.service.CuentaService;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import com.pichincha.bank.infrastructure.mapper.CuentaMapper;
import com.pichincha.bank.infrastructure.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    private final CuentaMapper cuentaMapper;

    @Override
    public void registerAccount(RegisterAccountRequest request) {
        CuentaEntity cuentaEntity = cuentaMapper.toCuentaEntity(request, true);
        cuentaRepository.save(cuentaEntity);
    }
}
