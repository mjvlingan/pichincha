package com.pichincha.bank.infrastructure.repository;

import com.pichincha.bank.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
