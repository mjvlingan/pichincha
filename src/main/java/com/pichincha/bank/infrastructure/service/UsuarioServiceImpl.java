package com.pichincha.bank.infrastructure.service;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.domain.service.UsuarioService;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import com.pichincha.bank.infrastructure.mapper.PersonaMapper;
import com.pichincha.bank.infrastructure.repository.ClienteRepository;
import com.pichincha.bank.infrastructure.mapper.ClienteMapper;
import com.pichincha.bank.infrastructure.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    private final ClienteMapper clienteMapper;
    private final PersonaMapper personaMapper;

    @Override
    public void saveUser(RegisterUserRequest request) {
        PersonaEntity personaEntity = personaMapper.toPersonaEntity(request);
        ClienteEntity clienteEntity = clienteMapper.toClienteEntity(request, true);
        personaRepository.save(personaEntity);
        clienteRepository.save(clienteEntity);
    }
}
