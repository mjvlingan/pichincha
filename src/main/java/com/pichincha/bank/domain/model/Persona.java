package com.pichincha.bank.domain.model;

import com.github.damianwajser.validator.annotation.enums.MatchEnum;
import com.github.damianwajser.validator.annotation.global.Size;
import com.pichincha.bank.domain.Constantes;
import com.pichincha.bank.domain.request.type.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Persona {
    private int idPersona;
    private String nombre;
    @MatchEnum(businessCode = Constantes.ENUM_VALIDATION,
            message = "{invalid.enum}", enumClass = GeneroEnum.class)
    private GeneroEnum genero;
    private String edad;
    @Size(max = 10, businessCode = Constantes.INVALID_IDENTIFICATION, message = "{invalid.identification}")
    private String identificacion;
    private String direccion;
    private String telefono;
}
