package com.alura.desafio.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListaDeLibros(

        @JsonAlias("count") Integer total,
        @JsonAlias("next") String siguiente,
        @JsonAlias("previous") String anterior,
        @JsonAlias("results") List<DatosLibros> listaDeLibros

) {
}