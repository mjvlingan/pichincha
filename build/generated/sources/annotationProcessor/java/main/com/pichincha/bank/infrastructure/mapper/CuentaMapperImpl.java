package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.request.RegisterAccountRequest;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-03T17:47:58-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public CuentaEntity toCuentaEntity(RegisterAccountRequest request, boolean estadoCuenta) {
        if ( request == null ) {
            return null;
        }

        CuentaEntity cuentaEntity = new CuentaEntity();

        if ( request != null ) {
            cuentaEntity.setSaldo( request.getSaldoInicial() );
            cuentaEntity.setIdCliente( request.getIdCliente() );
            cuentaEntity.setNumeroCuenta( request.getNumeroCuenta() );
            cuentaEntity.setTipoCuenta( request.getTipoCuenta() );
        }
        cuentaEntity.setEstadoCuenta( estadoCuenta );

        return cuentaEntity;
    }
}
