package com.pichincha.bank.infrastructure.mapper;

import com.pichincha.bank.domain.model.Cuenta;
import com.pichincha.bank.domain.model.Cuenta.CuentaBuilder;
import com.pichincha.bank.domain.request.type.TipoCuentaEnum;
import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-07T16:14:00-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public CuentaEntity toCuentaEntity(Integer idCliente, Cuenta cuenta, boolean estado) {
        if ( idCliente == null && cuenta == null ) {
            return null;
        }

        CuentaEntity cuentaEntity = new CuentaEntity();

        if ( idCliente != null ) {
            cuentaEntity.setIdCliente( idCliente );
        }
        if ( cuenta != null ) {
            cuentaEntity.setNumeroCuenta( cuenta.getNumeroCuenta() );
            if ( cuenta.getTipoCuenta() != null ) {
                cuentaEntity.setTipoCuenta( cuenta.getTipoCuenta().name() );
            }
            cuentaEntity.setSaldo( cuenta.getSaldo() );
        }
        cuentaEntity.setEstadoCuenta( estado );

        return cuentaEntity;
    }

    @Override
    public CuentaEntity toCuentaEntityUpdate(Cuenta cuenta, boolean estado) {
        if ( cuenta == null ) {
            return null;
        }

        CuentaEntity cuentaEntity = new CuentaEntity();

        if ( cuenta != null ) {
            cuentaEntity.setIdCuenta( cuenta.getIdCuenta() );
            cuentaEntity.setNumeroCuenta( cuenta.getNumeroCuenta() );
            if ( cuenta.getTipoCuenta() != null ) {
                cuentaEntity.setTipoCuenta( cuenta.getTipoCuenta().name() );
            }
            cuentaEntity.setSaldo( cuenta.getSaldo() );
        }
        cuentaEntity.setEstadoCuenta( estado );

        return cuentaEntity;
    }

    @Override
    public Cuenta toCuenta(CuentaEntity cuentaEntity) {
        if ( cuentaEntity == null ) {
            return null;
        }

        CuentaBuilder cuenta = Cuenta.builder();

        if ( cuentaEntity.getIdCuenta() != null ) {
            cuenta.idCuenta( cuentaEntity.getIdCuenta() );
        }
        cuenta.numeroCuenta( cuentaEntity.getNumeroCuenta() );
        if ( cuentaEntity.getTipoCuenta() != null ) {
            cuenta.tipoCuenta( Enum.valueOf( TipoCuentaEnum.class, cuentaEntity.getTipoCuenta() ) );
        }
        cuenta.saldo( cuentaEntity.getSaldo() );
        cuenta.estadoCuenta( cuentaEntity.isEstadoCuenta() );

        return cuenta.build();
    }
}
