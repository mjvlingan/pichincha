package com.pichincha.bank.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Cliente")
public class ClienteEntity {
    @Id
    private Integer idCliente;
    private Integer idPersona;
    private String password;
    private boolean estadoCliente;
}
