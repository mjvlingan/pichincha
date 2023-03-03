package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterAccountRequest;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface CuentaMapper {

    @Mapping(target = "idCuenta", ignore = true)
    @Mapping(target = "estadoCuenta", source = "estadoCuenta")
    @Mapping(target = "saldo", source = "request.saldoInicial")
    CuentaEntity toCuentaEntity(RegisterAccountRequest request, boolean estadoCuenta);

}
