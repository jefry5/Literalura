package com.desafio.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPortada(
       @JsonAlias("next") String siguiente,
       @JsonAlias("results") List<DatosLibro> resultados
) {}
