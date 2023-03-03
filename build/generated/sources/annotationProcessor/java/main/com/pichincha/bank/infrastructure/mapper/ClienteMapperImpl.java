package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T18:56:16-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity toClienteEntity(RegisterUserRequest request, boolean estado) {
        if ( request == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        if ( request != null ) {
            clienteEntity.setNombre( request.getNombre() );
            clienteEntity.setGenero( request.getGenero() );
            clienteEntity.setEdad( request.getEdad() );
            clienteEntity.setIdentificacion( request.getIdentificacion() );
            clienteEntity.setDireccion( request.getDireccion() );
            clienteEntity.setTelefono( request.getTelefono() );
            clienteEntity.setPassword( request.getPassword() );
        }
        clienteEntity.setEstadoCliente( estado );

        return clienteEntity;
    }
}
