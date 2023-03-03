package com.pichincha.bank.infrastructure.repository;

import com.pichincha.bank.infrastructure.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    List<CuentaEntity> findAllByIdCliente(int idCliente);
}
