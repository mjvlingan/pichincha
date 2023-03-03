package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterOperationRequest;
import com.pichincha.bank.infrastructure.entity.MovimientoEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-03T18:26:34-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class MovimientoMapperImpl implements MovimientoMapper {

    @Override
    public MovimientoEntity toMovimientoEntity(RegisterOperationRequest request, int saldoInicial, int saldoFinal) {
        if ( request == null ) {
            return null;
        }

        MovimientoEntity movimientoEntity = new MovimientoEntity();

        if ( request != null ) {
            movimientoEntity.setFecha( request.getFecha() );
            movimientoEntity.setIdCuenta( request.getIdCuenta() );
            if ( request.getTipoMovimiento() != null ) {
                movimientoEntity.setTipoMovimiento( request.getTipoMovimiento().name() );
            }
            movimientoEntity.setValor( request.getValor() );
        }
        movimientoEntity.setSaldoInicial( saldoInicial );
        movimientoEntity.setSaldoFinal( saldoFinal );

        return movimientoEntity;
    }
}
