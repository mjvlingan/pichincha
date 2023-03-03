package com.pichincha.bank.infrastructure.repository;

import com.pichincha.bank.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
