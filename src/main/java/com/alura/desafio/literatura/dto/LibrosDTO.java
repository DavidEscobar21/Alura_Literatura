package com.alura.desafio.literatura.dto;

import jakarta.persistence.Column;

import java.util.List;

public record LibrosDTO(
        long id,
        String titulo,
        List<String> idiomas,
        Integer totalDescargas
) {
}
