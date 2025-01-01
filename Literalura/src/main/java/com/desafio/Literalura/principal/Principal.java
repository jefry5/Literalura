package com.desafio.Literalura.principal;

import com.desafio.Literalura.controller.AutorController;
import com.desafio.Literalura.controller.LibroController;
import com.desafio.Literalura.repository.LibroRepository;

import java.util.Scanner;

public class Principal {
    private LibroController gestorLibro;
    private AutorController gestorAutor;
    private Scanner teclado;

    public Principal(){
        this.gestorLibro = new LibroController();
        this.gestorAutor = new AutorController();
        this.teclado = new Scanner(System.in);
    }

    public void mostrarMenu(){
        int opcion;
        do{
            mostrarOpcionesMenu();
            opcion = seleccionarOpcionMenu();
        }while(opcion != 0);
    }

    private void mostrarOpcionesMenu(){
        System.out.println("""
                **********************************
                    BIENVENIDO A LITERALURA
                **********************************
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                
                0 - Salir
                
               --Seleccione una opcion: """);
    }

    private int seleccionarOpcionMenu(){
        var opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1: gestorLibro.buscarLibroPorTitulo();
                    break;
            case 2: gestorLibro.listarLibros();
                    break;
            case 3: gestorAutor.listarAutores();
                    break;
            case 4: gestorAutor.listarAutoresVivosDuranteAnio();
                    break;
            case 5: gestorLibro.listarLibrosPorLenguaje();
                    break;
            case 0: System.out.println("¡¡¡HASTA LUEGO, GRACIAS POR UTILIZAR LITERALURA!!!\n");
                    break;
            default:
                System.out.println("Opción Invalida !!!\n");
                opcion = -1;
        }

        return opcion;
    }
}
