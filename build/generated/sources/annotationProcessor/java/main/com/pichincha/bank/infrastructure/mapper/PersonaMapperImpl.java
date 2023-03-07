package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Persona;
import com.pichincha.bank.domain.model.Persona.PersonaBuilder;
import com.pichincha.bank.domain.request.type.GeneroEnum;
import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-07T16:14:00-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaEntity toPersonaEntity(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setNombre( persona.getNombre() );
        if ( persona.getGenero() != null ) {
            personaEntity.setGenero( persona.getGenero().name() );
        }
        personaEntity.setEdad( persona.getEdad() );
        personaEntity.setIdentificacion( persona.getIdentificacion() );
        personaEntity.setDireccion( persona.getDireccion() );
        personaEntity.setTelefono( persona.getTelefono() );

        return personaEntity;
    }

    @Override
    public PersonaEntity toPersonaEntityUpdate(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setIdPersona( persona.getIdPersona() );
        personaEntity.setNombre( persona.getNombre() );
        if ( persona.getGenero() != null ) {
            personaEntity.setGenero( persona.getGenero().name() );
        }
        personaEntity.setEdad( persona.getEdad() );
        personaEntity.setIdentificacion( persona.getIdentificacion() );
        personaEntity.setDireccion( persona.getDireccion() );
        personaEntity.setTelefono( persona.getTelefono() );

        return personaEntity;
    }

    @Override
    public Persona toPersona(PersonaEntity personaEntity) {
        if ( personaEntity == null ) {
            return null;
        }

        PersonaBuilder persona = Persona.builder();

        if ( personaEntity.getIdPersona() != null ) {
            persona.idPersona( personaEntity.getIdPersona() );
        }
        persona.nombre( personaEntity.getNombre() );
        if ( personaEntity.getGenero() != null ) {
            persona.genero( Enum.valueOf( GeneroEnum.class, personaEntity.getGenero() ) );
        }
        persona.edad( personaEntity.getEdad() );
        persona.identificacion( personaEntity.getIdentificacion() );
        persona.direccion( personaEntity.getDireccion() );
        persona.telefono( personaEntity.getTelefono() );

        return persona.build();
    }
}
