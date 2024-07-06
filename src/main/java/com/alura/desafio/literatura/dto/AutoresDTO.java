package com.alura.desafio.literatura.dto;

public record AutoresDTO(
        long id,
        String nombre,
        Integer anoNacimiento,
        Integer anoFallecimiento
) {
}
