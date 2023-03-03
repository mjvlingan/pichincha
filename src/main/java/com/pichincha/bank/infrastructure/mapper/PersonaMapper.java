package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface PersonaMapper {

    @Mapping(target = "idPersona", ignore = true)
    PersonaEntity toPersonaEntity(RegisterUserRequest request);

}
