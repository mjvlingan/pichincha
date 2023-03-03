package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterUserRequest;
import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface ClienteMapper {

    @Mapping(target = "estadoCliente", source = "estado")
    @Mapping(target = "idCliente", ignore = true)
    ClienteEntity toClienteEntity(RegisterUserRequest request, boolean estado);

}
