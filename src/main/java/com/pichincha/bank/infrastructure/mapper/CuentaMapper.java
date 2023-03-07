package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface CuentaMapper {

    @Mapping(target = "idCuenta", ignore = true)
    @Mapping(target = "idCliente", source = "idCliente")
    @Mapping(target = "estadoCuenta", source = "estado")
    CuentaEntity toCuentaEntity(Integer idCliente, Cuenta cuenta, boolean estado);

    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "estadoCuenta", source = "estado")
    CuentaEntity toCuentaEntityUpdate(Cuenta cuenta, boolean estado);

    @Mapping(target = "movimientos", ignore = true)
    Cuenta toCuenta(CuentaEntity cuentaEntity);
}
