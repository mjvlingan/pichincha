package com.pichincha.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {
    public static final String RESPONSE_CODE_INTERNAL_ERROR = "500";
    public static final String RESPONSE_CODE_NOT_FOUND = "404";
    public static final String RESPONSE_CODE_CONFLICT = "409";

    public static final String ENUM_VALIDATION = "900";
    public static final String INVALID_IDENTIFICATION = "901";
}
