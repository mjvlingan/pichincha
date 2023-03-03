package com.pichincha.bank.domain.model;

import lombok.Data;

@Data
public class Persona {
    private int idPersona;
    private String nombre;
    private String genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
