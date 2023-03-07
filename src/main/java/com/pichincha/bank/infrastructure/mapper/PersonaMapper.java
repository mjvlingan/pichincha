package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface PersonaMapper {

    @Mapping(target = "idPersona", ignore = true)
    PersonaEntity toPersonaEntity(Persona persona);

    PersonaEntity toPersonaEntityUpdate(Persona persona);

    Persona toPersona(PersonaEntity personaEntity);
}
