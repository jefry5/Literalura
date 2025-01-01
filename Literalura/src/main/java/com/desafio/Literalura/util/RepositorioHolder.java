package com.desafio.Literalura.util;

import com.desafio.Literalura.repository.AutorRepository;
import com.desafio.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositorioHolder {
    private static LibroRepository libroRepositorio;
    private static AutorRepository autorRepositorio;

    @Autowired
    public RepositorioHolder(LibroRepository libroRepositorio,
                             AutorRepository autorRepositorio) {
        RepositorioHolder.libroRepositorio = libroRepositorio;
        RepositorioHolder.autorRepositorio = autorRepositorio;
    }

    public static LibroRepository getLibroRepositorio() {
        return libroRepositorio;
    }

    public static AutorRepository getAutorRepositorio() {
        return autorRepositorio;
    }
}
