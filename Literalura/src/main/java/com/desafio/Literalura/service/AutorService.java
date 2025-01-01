package com.desafio.Literalura.service;

import com.desafio.Literalura.model.Autor;
import com.desafio.Literalura.repository.AutorRepository;
import com.desafio.Literalura.util.RepositorioHolder;

import java.util.List;
import java.util.Scanner;

public class AutorService {
    private AutorRepository repositorio = RepositorioHolder.getAutorRepositorio();
    private Scanner teclado = new Scanner(System.in);

    public void listarAutores(){
        List<Autor> autores = repositorio.findAll();
        mostrarBusqueda(autores);
    }

    public void listarAutoresVivosDuranteAnio() {
        //Solicitamos el año
        System.out.println("Ingrese el año a buscar: ");
        Integer anio = teclado.nextInt();
        teclado.nextLine();

        //Buscamos los autores vivos en ese año en la Base de datos
        List<Autor> autores = repositorio.getAutoresVivosDeterminadoAnio(anio);
        System.out.println("***Autores vivos en el año " + anio + "***");
        if(!autores.isEmpty())
            mostrarBusqueda(autores);
        else
            System.out.println("--No hay autores registrados vivos en el año " + anio);
    }

    private void mostrarBusqueda(List<Autor> autores){
        autores.forEach(a -> System.out.printf("Autor: %s ,Año nacimiento: %s ,Año fallecimiento: %s \n",
                a.getNombre(),
                a.getAnio_nacimiento(),
                a.getAnio_fallecimiento()));

        //Esperamos a que el usuario ingrese cualquier tecla
        System.out.print("\nPresione cualquier tecla para continuar.....");
        teclado.nextLine();
    }


}
