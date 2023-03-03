package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.infrastructure.entity.MovimientoEntity;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@Generated
public interface MovimientoMapper {

    @Mapping(target = "idMovimiento", ignore = true)
    @Mapping(target = "saldoInicial", source = "saldoInicial")
    @Mapping(target = "saldoFinal", source = "saldoFinal")
    MovimientoEntity toMovimientoEntity(RegisterOperationRequest request, int saldoInicial, int saldoFinal);

}
