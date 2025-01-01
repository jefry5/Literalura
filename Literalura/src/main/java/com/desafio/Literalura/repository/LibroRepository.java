package com.desafio.Literalura.repository;

import com.desafio.Literalura.model.Lenguaje;
import com.desafio.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByLenguaje(Lenguaje lenguaje);
}
