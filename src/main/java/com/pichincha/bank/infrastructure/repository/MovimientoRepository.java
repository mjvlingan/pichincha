package com.pichincha.bank.infrastructure.repository;

import com.pichincha.bank.infrastructure.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Integer> {
    List<MovimientoEntity> findAllByIdCuenta(int idCuenta);
}
