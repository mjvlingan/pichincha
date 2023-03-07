package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.domain.model.Cliente.ClienteBuilder;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-07T16:14:00-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity toClienteEntity(Integer idPersona, Cliente cliente, boolean estado) {
        if ( idPersona == null && cliente == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        if ( idPersona != null ) {
            clienteEntity.setIdPersona( idPersona );
        }
        if ( cliente != null ) {
            clienteEntity.setPassword( cliente.getPassword() );
        }
        clienteEntity.setEstadoCliente( estado );

        return clienteEntity;
    }

    @Override
    public Cliente toCliente(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        ClienteBuilder cliente = Cliente.builder();

        if ( clienteEntity.getIdCliente() != null ) {
            cliente.idCliente( clienteEntity.getIdCliente() );
        }
        cliente.password( clienteEntity.getPassword() );
        cliente.estadoCliente( clienteEntity.isEstadoCliente() );

        return cliente.build();
    }
}
