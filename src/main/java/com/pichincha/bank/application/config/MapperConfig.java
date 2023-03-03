package com.pichincha.bank.application.config;

import com.pichincha.bank.infrastructure.mapper.ClienteMapper;
import com.pichincha.bank.infrastructure.mapper.CuentaMapper;
import com.pichincha.bank.infrastructure.mapper.MovimientoMapper;
import com.pichincha.bank.infrastructure.mapper.PersonaMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ClienteMapper clienteMapper(){
        return Mappers.getMapper(ClienteMapper.class);
    }

    @Bean
    public PersonaMapper personaMapper(){
        return Mappers.getMapper(PersonaMapper.class);
    }

    @Bean
    public CuentaMapper cuentaMapper(){
        return Mappers.getMapper(CuentaMapper.class);
    }

    @Bean
    public MovimientoMapper movimientoMapper(){
        return Mappers.getMapper(MovimientoMapper.class);
    }
}
