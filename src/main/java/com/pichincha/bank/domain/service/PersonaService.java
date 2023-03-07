package com.pichincha.bank.domain.service;

import com.github.damianwajser.exceptions.RestException;
import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.domain.request.RegisterPersonaRequest;
import com.pichincha.bank.domain.request.UpdatePersonaRequest;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    void savePersona(RegisterPersonaRequest request) throws RestException;

    Persona findPersona(int idPersona) throws RestException;

    List<PersonaEntity> getAllPersona() throws RestException;

    void updatePersona(UpdatePersonaRequest request) throws RestException;

    void deletePersona(int idPersona) throws RestException;
}
