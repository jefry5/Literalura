package com.desafio.Literalura.service;

import com.desafio.Literalura.controller.AutorController;
import com.desafio.Literalura.model.*;
import com.desafio.Literalura.repository.AutorRepository;
import com.desafio.Literalura.repository.LibroRepository;
import com.desafio.Literalura.util.RepositorioHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final int CANTIDAD_PAGINAS = 3;
    private ConsumirAPI consumirAPI;
    private ConvertirDatos conversor;
    private Scanner teclado;
    private List<Libro> listaLibros;
    private LibroRepository repositorioLibro = RepositorioHolder.getLibroRepositorio();
    private AutorRepository repositorioAutor = RepositorioHolder.getAutorRepositorio();

    public LibroService(){
        consumirAPI = new ConsumirAPI();
        conversor = new ConvertirDatos();
        teclado = new Scanner(System.in);
        listaLibros = new ArrayList<>();
    }

    public void buscarLibroPorTitulo(){
        String libroBuscar;
        Optional<Libro> libro;

        //Cargamos los datos de la API por primera vez
        cargarListaLibro();

        //Buscamos el libro en la API
        System.out.println("Ingrese el titulo a buscar: ");
        libroBuscar = teclado.nextLine();
        libro = listaLibros.stream()
                .filter(l -> l.getTitulo().toUpperCase().contains(libroBuscar.toUpperCase().trim()))
                .findFirst();

        if (libro.isPresent()){
            Libro l = libro.get();
            System.out.println(l.toString());

            //Verificamos la existencica del libro en la base de datos
            Optional<Libro> existeLibro = repositorioLibro.findByTituloContainsIgnoreCase(l.getTitulo());
            if (existeLibro.isEmpty()){
                //Evitamos la duplicación de actores
                List<Autor> autoresActualizados = new ArrayList<>();
                for (Autor autor : l.getAutores()) {
                    Optional<Autor> autorExistente = repositorioAutor.findByNombreContainsIgnoreCase(autor.getNombre());
                    if (autorExistente.isPresent()) {
                        //Almacenamos al autor existente
                        autoresActualizados.add(autorExistente.get());
                    } else {
                        //El nuevo autor lo guardamos en la base de datos
                        repositorioAutor.save(autor);
                        autoresActualizados.add(autor);
                    }
                }

                //Asignamos la lista de autores al libro
                l.setAutores(autoresActualizados);

                repositorioLibro.save(l);
            }else{
                System.out.println("Libro ya registrado");
            }
        }else{
            System.out.println("Libro no encontrado");
        }
    }

    public void listarLibros(){
        List<Libro> libros = repositorioLibro.findAll();

        //Mostramos la lista de libros
        mostrarListaLibro(libros);
    }

    public void listarLibrosPorLenguaje(){
        //Solicitamos el lenguaje a listar
        System.out.println("""
                Ingrese el lenguaje: 
                -Ejemplo: Inglés""");
        String lenguajeListar = teclado.nextLine();

        //Convertimos el lenguaje a una categoria Lenguaje
        Lenguaje lenguaje = Lenguaje.fromEspanol(lenguajeListar);

        //Extraemos y mostramos los datos coincidentes de la Base de datos
        List<Libro> librosFiltro = repositorioLibro.findByLenguaje(lenguaje);
        System.out.println("***Libros en " + lenguaje + "***");
        if (!librosFiltro.isEmpty())
            mostrarListaLibro(librosFiltro);
        else
            System.out.println("\n--No hay libros registrados en " + lenguaje);
    }

    private List<Libro> obtenerListaLibros(){
        List<DatosPortada> listaDatos = new ArrayList<>();
        String json = consumirAPI.obtenerDatos(URL_BASE);
        String next = "";
        int contador = 0;

        //Extrae las cantidad de páginas indicadas de la API
        while(!next.equals("null") && contador < CANTIDAD_PAGINAS){
            DatosPortada datos = conversor.obtenerDatos(json, DatosPortada.class);
            listaDatos.add(datos);
            next = datos.siguiente();
            if (!next.equals("null"))
                json = consumirAPI.obtenerDatos(next);
            contador++;
        }

        //Convertimos los datos a la clase Libro
        List<DatosLibro> listaLibros = listaDatos.stream()
                .flatMap(p -> p.resultados().stream())
                .collect(Collectors.toList());

        return listaLibros.stream()
                .map(l -> new Libro(l))
                .collect(Collectors.toList());
    }

    private void cargarListaLibro(){
        if (listaLibros.isEmpty()){

            System.out.println("""
                    ------------------
                    | Cargando datos |
                    |   de la API... |
                    ------------------""");
            listaLibros = obtenerListaLibros();
            System.out.println("¡¡¡Datos de API cargados!!!");
        }
    }

    private void mostrarListaLibro(List<Libro> libros){
        libros.forEach(l -> System.out.printf("Titulo: %s ,Autor(es): %s ,Lenguaje: %s ,Descargas: %s\n",
                l.getTitulo(),
                l.getAutores().toString(),
                l.getLenguaje(),
                l.getCantidadDescargas()));

        //Esperamos a que el usuario ingrese cualquier tecla
        System.out.print("\nPresione cualquier tecla para continuar.....");
        teclado.nextLine();
    }
}
