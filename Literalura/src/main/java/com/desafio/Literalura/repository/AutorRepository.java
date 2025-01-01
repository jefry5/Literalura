package com.desafio.Literalura.repository;

import com.desafio.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a " +
            "WHERE a.anio_nacimiento <= :anio AND a.anio_fallecimiento >= :anio")
    List<Autor> getAutoresVivosDeterminadoAnio(Integer anio);
}
