package com.pichincha.bank.infrastructure.service;

import com.github.damianwajser.exceptions.RestException;
import com.github.damianwajser.exceptions.impl.badrequest.NotFoundException;
import com.github.damianwajser.exceptions.impl.servererror.InternalServerErrorException;
import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.domain.request.RegisterPersonaRequest;
import com.pichincha.bank.domain.request.UpdatePersonaRequest;
import com.pichincha.bank.domain.service.PersonaService;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import com.pichincha.bank.infrastructure.mapper.PersonaMapper;
import com.pichincha.bank.infrastructure.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.pichincha.bank.domain.Constantes.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    @Override
    public void savePersona(RegisterPersonaRequest request) throws RestException {
        log.info("Inicio registro persona");
        log.debug("Request: {}", request);
        PersonaEntity personaEntity = personaMapper.toPersonaEntity(request.getPersona());
        try{
            log.info("Guardando persona");
            personaRepository.save(personaEntity);
        }
        catch (Exception e) {
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.save}");
        }
    }

    @Override
    public Persona findPersona(int idPersona) throws RestException {
        log.info("Inicio obtener persona");
        log.debug("Request: {}", idPersona);
        Optional<PersonaEntity> result = personaRepository.findById(idPersona);
        if(result.isPresent()){
            return personaMapper.toPersona(result.get());
        }
        else{
            log.error("Error al encontrar persona");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public List<PersonaEntity> getAllPersona() {
        return personaRepository.findAll();
    }

    @Override
    public void updatePersona(UpdatePersonaRequest request) throws RestException {
        log.info("Inicio actualizacion persona");
        if(personaRepository.existsById(request.getPersona().getIdPersona())){
            log.debug("Request: {}", request);
            PersonaEntity personaEntity = personaMapper.toPersonaEntityUpdate(request.getPersona());
            try{
                personaRepository.save(personaEntity);
            }
            catch (Exception e){
                log.error("Error en metodo: {}", e.getMessage());
                throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.update}");
            }
        }
        else{
            log.error("No se encontro la persona");
            throw new NotFoundException(RESPONSE_CODE_NOT_FOUND, "{error.find}");
        }
    }

    @Override
    public void deletePersona(int idPersona) throws RestException {
        try{
            log.info("Inicio eliminacion persona");
            personaRepository.deleteById(idPersona);
        }
        catch (Exception e){
            log.error("Error en metodo: {}", e.getMessage());
            throw new InternalServerErrorException(RESPONSE_CODE_INTERNAL_ERROR, "{error.delete}");
        }
    }
}
