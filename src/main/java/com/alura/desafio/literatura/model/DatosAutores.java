package com.alura.desafio.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year")  Integer anoNacimiento,
        @JsonAlias("death_year") Integer anoFallecimiento
) {
}
