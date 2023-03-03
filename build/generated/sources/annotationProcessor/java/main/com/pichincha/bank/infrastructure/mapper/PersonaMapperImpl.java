package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-03T17:47:58-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaEntity toPersonaEntity(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setNombre( request.getNombre() );
        personaEntity.setGenero( request.getGenero() );
        personaEntity.setEdad( request.getEdad() );
        personaEntity.setIdentificacion( request.getIdentificacion() );
        personaEntity.setDireccion( request.getDireccion() );
        personaEntity.setTelefono( request.getTelefono() );

        return personaEntity;
    }
}
