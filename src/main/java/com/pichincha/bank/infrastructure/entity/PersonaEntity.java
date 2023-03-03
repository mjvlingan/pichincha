package com.pichincha.bank.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Persona")
public class PersonaEntity {
    @Id
    private Integer idPersona;
    private String nombre;
    private String genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
