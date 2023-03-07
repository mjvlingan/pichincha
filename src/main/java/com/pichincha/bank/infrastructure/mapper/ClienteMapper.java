package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Cliente;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface ClienteMapper {

    @Mapping(target = "idPersona", source = "idPersona")
    @Mapping(target = "estadoCliente", source = "estado")
    @Mapping(target = "idCliente", ignore = true)
    ClienteEntity toClienteEntity(int idPersona, Cliente cliente, boolean estado);

    @Mapping(target = "cuentas", ignore = true)
    Cliente toCliente(ClienteEntity clienteEntity);

}
